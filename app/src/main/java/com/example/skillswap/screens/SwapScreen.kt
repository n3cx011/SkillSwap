package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwapScreen(
    navController: NavController,
    onBackClick: () -> Unit = {}
) {
    val backgroundDark = Color(0xFF141414)
    val brandBlue = Color(0xFF1E59C9)
    val buttonYellow = Color(0xFFFFD500)

    // State for the Tabs
    var selectedTab by remember { mutableIntStateOf(0) } // 0 = SEND, 1 = RECEIVED

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("SWAP", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundDark)
            )
        },
        bottomBar = {
            HomeBottomBar(navController = navController, currentRoute = "swap")
        },
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 1. Custom Tabs (SEND / RECEIVED)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundDark)
            ) {
                TabItem(title = "SEND", isSelected = selectedTab == 0, modifier = Modifier.weight(1f)) { selectedTab = 0 }
                TabItem(title = "RECEIVED", isSelected = selectedTab == 1, modifier = Modifier.weight(1f)) { selectedTab = 1 }
            }

            HorizontalDivider(color = Color.DarkGray, thickness = 1.dp)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // 2. Filter & More Options Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Filter Button
                    Row(
                        modifier = Modifier
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .clickable { /* Open Filter */ },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.FilterAlt, contentDescription = "Filter", tint = Color.White, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("FILTER", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    // 3-Dots Menu with Dropdown
                    SwapStatusMenu()
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 3. Swap Cards List
                if (selectedTab == 0) {
                    SwapCard(
                        name = "Robbie Harrison",
                        exp = "8year +",
                        likes = "3K+",
                        swaps = "65+",
                        comments = "146",
                        imageRes = R.drawable.avatar_robbie, // Ensure this matches your drawable!
                        brandBlue = brandBlue,
                        buttonYellow = buttonYellow
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SwapCard(
                        name = "James Smith",
                        exp = "5year +",
                        likes = "5K+",
                        swaps = "60+",
                        comments = "128",
                        imageRes = R.drawable.avatar_james, // Ensure this matches your drawable!
                        brandBlue = brandBlue,
                        buttonYellow = buttonYellow
                    )
                } else {
                    // Placeholder for "RECEIVED" tab
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        Text("No received requests yet.", color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 4. Bottom Notice Text
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.FilterAlt, contentDescription = "Notice", tint = Color.Gray, modifier = Modifier.size(24.dp)) // Using generic icon as placeholder for the (!) icon
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("The Response of the person will be notified to you your\nprovided ")
                            withStyle(style = SpanStyle(color = Color(0xFFE57373))) { append("gmail") } // Subtle red/orange
                            append(" account for more information\nContact on ")
                            withStyle(style = SpanStyle(color = Color(0xFFE57373))) { append("Help and Support") }
                            append(".")
                        },
                        color = Color.Gray,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val brandBlue = Color(0xFF1E59C9)
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = if (isSelected) brandBlue else Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Underline indicator
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(3.dp)
                .background(if (isSelected) brandBlue else Color.Transparent, RoundedCornerShape(1.dp))
        )
    }
}

@Composable
fun SwapStatusMenu() {
    var expanded by remember { mutableStateOf(false) }
    val buttonYellow = Color(0xFF3F51B5)

    Box {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .size(32.dp)
                .background(buttonYellow, RoundedCornerShape(4.dp))
        ) {
            Icon(Icons.Default.MoreVert, contentDescription = "Status Options", tint = Color.Black)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF2C2C2C))
        ) {
            DropdownMenuItem(
                text = { Text("Seen", color = Color.White) },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Accepted", color = Color.White) },
                onClick = { expanded = false }
            )
            DropdownMenuItem(
                text = { Text("Declined", color = Color.White) },
                onClick = { expanded = false }
            )
        }
    }
}

@Composable
fun SwapCard(
    name: String, exp: String, likes: String, swaps: String, comments: String, imageRes: Int,
    brandBlue: Color, buttonYellow: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = brandBlue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    // Avatar
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = name,
                        modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    // Info
                    Column {
                        Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(
                            text = buildAnnotatedString {
                                append("Musician - ")
                                withStyle(style = SpanStyle(color = buttonYellow)) { append("★★★★") }
                                append("★")
                            },
                            color = Color.White, fontSize = 12.sp
                        )
                        Text(
                            text = buildAnnotatedString {
                                append("Experience . ")
                                withStyle(style = SpanStyle(color = buttonYellow)) { append(exp) }
                            },
                            color = Color.White, fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Stats Row
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Likes", tint = Color.White, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(likes, color = Color.White, fontSize = 12.sp)

                            Spacer(modifier = Modifier.width(12.dp))

                            Icon(Icons.Outlined.SwapHoriz, contentDescription = "Swaps", tint = Color.White, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(swaps, color = Color.White, fontSize = 12.sp)

                            Spacer(modifier = Modifier.width(12.dp))

                            Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Comments", tint = Color.White, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(comments, color = Color.White, fontSize = 12.sp)
                        }
                    }
                }

                // Bookmark Icon
                Icon(Icons.Default.BookmarkBorder, contentDescription = "Save", tint = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // UNSWAP Button
            Button(
                onClick = { /* Handle Unswap */ },
                modifier = Modifier.fillMaxWidth().height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF111111)), // Dark contrast button
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("UNSWAP", color = buttonYellow, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SwapScreenPreview() {
    SwapScreen(navController = rememberNavController())
}