package com.example.skillswap.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R
import androidx.navigation.NavController

// Define standard colors used in this screen
val BackgroundDark = Color(0xFF141414)
val BrandBlue = Color(0xFF1E59C9)

@Composable
fun HomeScreen(onSearchClick: () -> Unit = {},
               navController: NavController,
) {
    // Scaffold handles the TopBar, BottomBar, and Main Content automatically
    Scaffold(
        topBar = { HomeTopBar(onSearchClick = onSearchClick) },
        bottomBar = {
            HomeBottomBar(navController = navController, currentRoute = "home")
                    },
        containerColor = BackgroundDark
    ) { paddingValues ->
        // The main scrollable content area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Swap, learn, grow",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))
            MostCollaboratedCard()

            Spacer(modifier = Modifier.height(32.dp))

            // Free Learning Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Free Learning",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "See more",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* View all courses */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            FreeLearningCard()

            // Add a little bottom spacer so it doesn't hug the nav bar
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onSearchClick: () -> Unit) {
    TopAppBar(
        title = { },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
            }
            IconButton(onClick = { /* Premium */ }) {
                // Now using your custom crown icon!
                Image(
                    painter = painterResource(id = R.drawable.ic_crown),
                    contentDescription = "Premium",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundDark)
    )
}

@Composable
fun MostCollaboratedCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = BrandBlue)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Most Collaborated",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Discover the most accomplished and influential professionals",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                // Now using your actual grouped avatars image!
                Image(
                    painter = painterResource(id = R.drawable.avatars_cluster),
                    contentDescription = "Top collaborators",
                    modifier = Modifier.height(32.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem(icon = Icons.Outlined.FavoriteBorder, text = "20k+")
                Spacer(modifier = Modifier.width(16.dp))
                StatItem(icon = Icons.Default.SwapHoriz, text = "500+")
                Spacer(modifier = Modifier.width(16.dp))
                StatItem(icon = Icons.Outlined.ChatBubbleOutline, text = "1k+")

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "See more",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Action */ }
                )
            }
        }
    }
}

@Composable
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Black, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FreeLearningCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(4.dp, BrandBlue),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Course Image (Placeholder until you add course_thumbnail.png)
            Image(
                painter = painterResource(id = R.drawable.course_thumbnail),
                contentDescription = "UI/UX Course Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop // This ensures the image fills the space beautifully without stretching
            )

            // Course Details
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "21,390 students", color = Color.DarkGray, fontSize = 12.sp)
                    Text(text = "10 h 26m", color = Color.DarkGray, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Learn UI/UX Design, Figma and Prototyping",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "- Brad Frost", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Icon(imageVector = Icons.Outlined.BookmarkBorder, contentDescription = "Save", tint = Color.DarkGray)
                }
            }
        }
    }
}

@Composable
fun HomeBottomBar(navController: NavController, currentRoute: String = "") {
    val brandBlue = Color(0xFF1E59C9)

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White,
        tonalElevation = 8.dp
    ) {
        // 1. Home Tab
        NavigationBarItem(
            icon = {
                if (currentRoute == "home") {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(brandBlue), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
                    }
                } else {
                    Icon(Icons.Outlined.Home, contentDescription = "Home", tint = Color.White)
                }
            },
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") {
                    navController.navigate("home"){
                        popUpTo("home"){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )

        // 2. Favorites/Messages Tab (Star Icon)
        NavigationBarItem(
            icon = {
                if (currentRoute == "messages") {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(brandBlue), contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.StarBorder, contentDescription = "Messages", tint = Color.White)
                    }
                } else {
                    Icon(Icons.Outlined.StarBorder, contentDescription = "Messages", tint = Color.White)
                }
            },
            selected = currentRoute == "messages",
            onClick = {
                if (currentRoute != "messages") {
                    navController.navigate("messages"){
                        popUpTo("home"){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )

        // 3. Swap Tab (Center)
        NavigationBarItem(
            icon = {
                // Optional: Highlight it with a blue circle if currentRoute == "swap"
                if (currentRoute == "swap") {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFF1E59C9)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.SwapHoriz, contentDescription = "Swap", tint = Color.White)
                    }
                } else {
                    Icon(Icons.Outlined.SwapHoriz, contentDescription = "Swap", tint = Color.White)
                }
            },
            selected = currentRoute == "swap",
            onClick = {
                if (currentRoute != "swap") {
                    navController.navigate("swap"){
                        popUpTo("home"){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )

        // 4. Profile Tab
        NavigationBarItem(
            icon = {
                if (currentRoute == "profile") {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(brandBlue), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White)
                    }
                } else {
                    Icon(Icons.Outlined.Person, contentDescription = "Profile", tint = Color.White)
                }
            },
            selected = currentRoute == "profile",
            onClick = {
                if (currentRoute != "profile") {
                    navController.navigate("profile"){
                        popUpTo("home"){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = androidx.navigation.compose.rememberNavController())
}