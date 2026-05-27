package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skillswap.R

// 1. We create a blueprint for what a "Chat Message" needs to contain
data class ChatData(
    val name: String,
    val message: String,
    val time: String,
    val imageRes: Int,
    val statusColor: Color,
    val isOnline: Boolean = false,
    val timeBadge: String? = null
)

@Composable
fun MessageScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    onChatClick: () -> Unit
) {
    val brandBlue = Color(0xFF1E59C9)
    val backgroundDark = Color(0xFF141414)
    val dividerColor = Color.White.copy(alpha = 0.2f)

    // 2. We build our actual list of messages using the blueprint
    val chatList = listOf(
        ChatData("Robbie Harrison", "sure! I can do 2PM tomorrow......", "11:10", R.drawable.avatar_robbie, brandBlue, timeBadge = "1m"),
        ChatData("Thomas Marks", "Hi mr.thomas", "10:50", R.drawable.avatar_thomas, Color(0xFFFFD54F), isOnline = true),
        ChatData("James Smith", "Hey what's up", "09:18", R.drawable.avatar_james, Color(0xFFFFD54F), timeBadge = "9m"),
        ChatData("Selina Andreoson", "dear student, please can your....", "Yesterday", R.drawable.avatar_selina, brandBlue, isOnline = true),
        ChatData("Elvin Bond", "Hey there! We're thrilled to have you join ...", "Friday", R.drawable.avatar_elvin, Color(0xFFFFD54F)),
        ChatData("Neha Mayumi", "how are you dr..", "Friday", R.drawable.avatar_neha, brandBlue, isOnline = true)
    )

    Scaffold(
        topBar = { MessageTopBar(onBackClick) },
        // Using a dedicated bottom bar since the 'Star' tab is currently active in this design
        bottomBar = {
            HomeBottomBar(navController = navController, currentRoute = "messages")
        },
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Secondary Blue Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brandBlue)
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
                Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "Options", tint = Color.Black)
            }

            // Archived Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Work, contentDescription = "Archived", tint = Color.White, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Archived", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
                Text("Mark all as Clean", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }

            HorizontalDivider(color = dividerColor, thickness = 1.dp)

            // 3. The LazyColumn efficiently draws only the items currently visible on the screen
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(chatList) { chat ->
                    ChatItem(chat = chat, onClick = {
                        if (chat.name == "Robbie Harrison"){
                            onChatClick()
                        }
                    })
                    HorizontalDivider(color = dividerColor, thickness = 1.dp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text("Message", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Medium)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { /* Search */ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}

@Composable
fun ChatItem(chat: ChatData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar Box with Badges
        Box(
            modifier = Modifier.size(56.dp)
        ) {
            Image(
                painter = painterResource(id = chat.imageRes),
                contentDescription = chat.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Online Green Dot Status
            if (chat.isOnline) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-4).dp, y = (-4).dp)
                        .background(Color(0xFF00E676), CircleShape)
                )
            }

            // Time Badge (e.g., '1m', '9m')
            if (chat.timeBadge != null) {
                Text(
                    text = chat.timeBadge,
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 4.dp, y = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Name and Message Column
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.message,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis // Adds the '...' if text is too long
            )
        }

        // Time and Status Dot Column
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = chat.time,
                color = Color.White,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(chat.statusColor, CircleShape)
            )
        }
    }
}

// A specific bottom bar for this screen to highlight the Star tab
@Composable
fun MessageBottomBar() {
    val brandBlue = Color(0xFF1E59C9)

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home", tint = Color.White) },
            selected = false,
            onClick = { /* Handle Home Click */ }
        )
        NavigationBarItem(
            icon = {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(brandBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.StarBorder, contentDescription = "Messages/Favorites", tint = Color.White, modifier = Modifier.size(28.dp))
                    // Tiny red notification dot
                    Box(modifier = Modifier.align(Alignment.TopEnd).offset(x = (-12).dp, y = 12.dp).size(6.dp).background(Color.Red, CircleShape))
                }
            },
            selected = true,
            onClick = { /* Handle Messages */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.SwapHoriz, contentDescription = "Swap", tint = Color.White) },
            selected = false,
            onClick = { /* Handle Swap */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile", tint = Color.White) },
            selected = false,
            onClick = { /* Handle Profile */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MessageScreenPreview() {
    MessageScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        onBackClick = {},
        onChatClick = {}
    )
}