package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

// Blueprint for a booking item
data class BookingData(
    val name: String,
    val service: String,
    val time: String,
    val date: String,
    val isConfirmed: Boolean,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingScreen(
    onBackClick: () -> Unit
) {
    val backgroundDark = Color(0xFF1E1E1E)

    // State to track which tab is currently selected
    var selectedTab by remember { mutableStateOf("Active") }

    // Dummy data for bookings
    val bookings = listOf(
        BookingData("Anna Smith", "Plumbing Repair", "2:20 PM", "3rd Jan", true, R.drawable.avatar_anna),
        BookingData("Alex Johnson", "Photography Session", "5:00 PM", "2nd Jan", true, R.drawable.avatar_alex),
        BookingData("Jayden Rodrigo", "Guitar Lessons", "8:05 AM", "2nd Jan", false, R.drawable.avatar_jayden)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My Booking",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                // Adding a dummy action to perfectly center the title (balances the back arrow)
                actions = { Spacer(modifier = Modifier.width(48.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        // Reusing the bottom bar you already built in ProfileScreen.kt!
        bottomBar = { ProfileBottomBar() },
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Custom Tab Switcher
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF111111), RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                TabButton(
                    text = "Active",
                    isSelected = selectedTab == "Active",
                    modifier = Modifier.weight(1f),
                    onClick = { selectedTab = "Active" }
                )
                TabButton(
                    text = "History",
                    isSelected = selectedTab == "History",
                    modifier = Modifier.weight(1f),
                    onClick = { selectedTab = "History" }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Scrollable List of Bookings
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // If they click "History", we could show a different list. For now, we show the same data for demo purposes.
                items(bookings) { booking ->
                    BookingCard(booking = booking)
                }
                item { Spacer(modifier = Modifier.height(24.dp)) } // Bottom padding
            }
        }
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.Black else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun BookingCard(booking: BookingData) {
    val brandBlue = Color(0xFF1E59C9)
    val buttonYellow = Color(0xFFFFC107)
    val successGreen = Color(0xFF81C784)
    val pendingOrange = Color(0xFFFF7043)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = brandBlue)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = booking.imageRes),
                    contentDescription = booking.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Details Column
            Column(modifier = Modifier.weight(1f)) {
                // Name & Time Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = booking.name, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = booking.time, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Service & Date Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = booking.service, color = Color.Black.copy(alpha = 0.6f), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = booking.date, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Status Indicator
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (booking.isConfirmed) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Confirmed", tint = successGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Confirm", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    } else {
                        Icon(Icons.Default.Schedule, contentDescription = "Pending", tint = pendingOrange, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Pending", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Action Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { /* Contact Action */ },
                        modifier = Modifier.weight(1f).height(32.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Contact", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Button(
                        onClick = { /* Clear Action */ },
                        modifier = Modifier.weight(1f).height(32.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Clear", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyBookingScreenPreview() {
    MyBookingScreen(onBackClick = {})
}