package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.skillswap.R // Update to your R package!
import androidx.navigation.NavController

// Blueprint for a single message
data class ChatMessage(
    val text: String,
    val time: String,
    val isFromMe: Boolean
)

@Composable
fun ChatScreen(
    onBackClick: () -> Unit,
    onCheckoutClick: () -> Unit = {},
    navController: NavController,
) {
    val backgroundDark = Color(0xFF141414)

    // State to hold what the user is typing
    var inputText by remember { mutableStateOf("") }
    // State to control if the Price Breakdown modal is visible
    var showPriceDialog by remember { mutableStateOf(false) }

    // Dummy data for the chat history
    val messages = listOf(
        ChatMessage("Hi! I saw your profile and I'm interested in guitar lessons.", "10:30 am", true),
        ChatMessage("Hello! Thanks for reaching out. I'd be happy to help you learn guitar!", "10:33 am", false),
        ChatMessage("What days work best for you?", "10:35 am", false),
        ChatMessage("I'm flexible! How about tomorrow at 2 PM?", "10:50 am", true),
        ChatMessage("Sure! I can do 2 PM tomorrow. Would you like me to send you an offer?", "11:10 am", false)
    )

    // Quick reply suggestions
    val quickReplies = listOf("Are you available today", "What's your rate?", "Your free trial")

    Scaffold(
        topBar = { ChatTopBar(onBackClick) },
        bottomBar = { HomeBottomBar(navController = navController, currentRoute = "your_screen_name_here") }, // Reusing your existing bottom bar
        containerColor = backgroundDark,
        // This ensures the input field gets pushed up when the keyboard opens
        modifier = Modifier.imePadding()
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            Column(modifier = Modifier.fillMaxSize()) {
                // 1. The scrollable chat history
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(messages) { msg ->
                        ChatBubble(message = msg)
                    }
                }

                // 2. Quick Replies Row
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(quickReplies) { reply ->
                        SuggestionChip(text = reply)
                    }
                }

                // 3. The Input Area
                ChatInputArea(
                    text = inputText,
                    onTextChange = { inputText = it },
                    onSendOfferClick = { showPriceDialog = true } // Triggers the modal!
                )
            }

            // 4. The Overlay Dialog (Only shows if showPriceDialog is true)
            if (showPriceDialog) {
                PriceBreakdownDialog(onDismiss = { showPriceDialog = false })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(onBackClick: () -> Unit) {
    val brandBlue = Color(0xFF1E59C9)

    Column {
        TopAppBar(
            title = { Text("Message", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Medium) },
            navigationIcon = {
                IconButton(onClick = onBackClick) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White) }
            },
            actions = {
                IconButton(onClick = { }) { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White) }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
        )

        // The secondary blue bar with user details
        Row(
            modifier = Modifier.fillMaxWidth().background(brandBlue).padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_robbie),
                    contentDescription = "Robbie",
                    modifier = Modifier.size(36.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Robbie Harrison", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Row {
                Icon(Icons.Default.Phone, contentDescription = "Call", tint = Color.Black, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.MoreHoriz, contentDescription = "More", tint = Color.Black)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val myBubbleColor = Color(0xFF4A4A4A) // Dark gray for sent messages
    val theirBubbleColor = Color(0xFF2C2C2C) // Darker gray for received

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isFromMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!message.isFromMe) {
            Image(
                painter = painterResource(id = R.drawable.avatar_robbie),
                contentDescription = null,
                modifier = Modifier.size(24.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(horizontalAlignment = if (message.isFromMe) Alignment.End else Alignment.Start) {
            Box(
                modifier = Modifier
                    .background(
                        color = if (message.isFromMe) myBubbleColor else theirBubbleColor,
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (message.isFromMe) 16.dp else 4.dp,
                            bottomEnd = if (message.isFromMe) 4.dp else 16.dp
                        )
                    )
                    .padding(16.dp)
                    .widthIn(max = 250.dp) // Prevents bubble from stretching all the way across
            ) {
                Text(text = message.text, color = Color.White, fontSize = 14.sp, lineHeight = 20.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message.time, color = Color.Gray, fontSize = 10.sp)
        }
    }
}

@Composable
fun SuggestionChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFF333333), RoundedCornerShape(20.dp))
            .clickable { /* Handle Chip Click */ }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun ChatInputArea(
    text: String,
    onTextChange: (String) -> Unit,
    onSendOfferClick: () -> Unit
) {
    val brandBlue = Color(0xFF1E59C9)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Top Row: The actual text input and the Offer button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // THE FIX: This is the real text box that uses your variables!
            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type your message here...", color = Color.Gray, fontSize = 14.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                maxLines = 3 // Allows the box to grow slightly if they type a long message
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onSendOfferClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90CAF9)), // Light blue
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                modifier = Modifier.height(28.dp)
            ) {
                Text("Send Offer $50", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Bottom Row: Attachments and Send Button (Unchanged)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.SentimentSatisfiedAlt, contentDescription = "Emoji", tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Default.AttachFile, contentDescription = "Attach", tint = Color.Gray)

            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = Modifier.background(brandBlue, CircleShape).padding(8.dp)) {
                Icon(Icons.Default.Mic, contentDescription = "Voice", tint = Color.White, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Handle actual message sending here later */ },
                colors = ButtonDefaults.buttonColors(containerColor = brandBlue),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text("Send Now", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PriceBreakdownDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .background(Color(0xFF4A4A4A).copy(alpha = 0.95f), RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Column {
                Text("Price Breakdown", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Service Fee (1 hour)", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                    Text("$50.00", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Platform Fee", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                    Text("$5.00", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("$55.00", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        onBackClick = {})
}