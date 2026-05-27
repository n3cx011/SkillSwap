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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.skillswap.R
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    navController: NavController,
    onBackClick: () -> Unit, // Optional, depending on if you want a back button
    onBookingClick: () -> Unit = {},
    onEarningsClick: () -> Unit = {},
    onFeedbackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onRewardsClick: () -> Unit = {}
) {
    val backgroundDark = Color(0xFF1E1E1E) // Slightly lighter dark for the main background
    val headerBlack = Color.Black

    Scaffold(
        bottomBar = { HomeBottomBar(navController = navController, currentRoute = "profile") },
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // 1. Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(headerBlack)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Image with a fallback box
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.DarkGray)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_alexa),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Alexa Rodrigo",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Usr Since 2023",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Menu Items List
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuListItem(title = "My Booking", onClick = onBookingClick)
                MenuListItem(title = "Earnings", onClick = onEarningsClick)
                MenuListItem(title = "Feedback", onClick = onFeedbackClick)
                MenuListItem(title = "Settings", onClick = onSettingsClick)
                MenuListItem(title = "Rewards", onClick = onRewardsClick)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun MenuListItem(title: String, onClick: () -> Unit) {
    val brandBlue = Color(0xFF1E59C9)
    val cardDark = Color(0xFF111111) // Very dark gray, almost black

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardDark),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 24.dp)
            )

            // The blue highlight indicator on the right edge
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight(0.6f) // Takes up 60% of the card's height
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .background(brandBlue)
            )
        }
    }
}

// A specific bottom bar for this screen to highlight the Profile tab
@Composable
fun ProfileBottomBar() {
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
            icon = { Icon(Icons.Outlined.StarBorder, contentDescription = "Favorites", tint = Color.White) },
            selected = false,
            onClick = { /* Handle Favorites */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.SwapHoriz, contentDescription = "Swap", tint = Color.White) },
            selected = false,
            onClick = { /* Handle Swap */ }
        )
        NavigationBarItem(
            icon = {
                // Blue highlighted circle for the active Profile tab
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(brandBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.Person, contentDescription = "Profile", tint = Color.White)
                }
            },
            selected = true,
            onClick = { /* Handle Profile */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        onBackClick = {})
}