package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingCheckoutScreen(onBackClick: () -> Unit, onProceedClick: () -> Unit) {
    val backgroundDark = Color(0xFF141414)
    val cardGray = Color(0xFF2C2C2C)
    val brandBlue = Color(0xFF1E59C9)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Checkout", color = Color.White, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundDark)
            )
        },
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Service Details Card
            Card(
                colors = CardDefaults.cardColors(containerColor = cardGray),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.img_guitar), // Ensure you have this asset
                            contentDescription = "Guitar",
                            modifier = Modifier.size(60.dp, 40.dp).clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Guitar Lessons with", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("Robbie Harrison", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                            Text("Beginner to Intermediate Level", color = Color.Gray, fontSize = 10.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(16.dp))

                    DetailRow(icon = Icons.Default.CalendarMonth, title = "Saturday, January 10, 2026", subtitle = "Tomorrow")
                    Spacer(modifier = Modifier.height(16.dp))
                    DetailRow(icon = Icons.Default.Schedule, title = "2:00 PM - 3:00 PM", subtitle = "60 minutes session")
                    Spacer(modifier = Modifier.height(16.dp))
                    DetailRow(icon = Icons.Default.LocationOn, title = "2706 Music Studio Downtown", subtitle = "123 Harmony Street, Suite 4B")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Price Breakdown Card
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF9E9E9E)), // Lighter gray from design
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Price Breakdown", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Service Fee (1 hour)", color = Color.White, fontWeight = FontWeight.Medium)
                        Text("$50.00", color = Color.White, fontWeight = FontWeight.Medium)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Platform Fee", color = Color.White, fontWeight = FontWeight.Medium)
                        Text("$5.00", color = Color.White, fontWeight = FontWeight.Medium)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Total", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Button(
                                onClick = onProceedClick,
                                colors = ButtonDefaults.buttonColors(containerColor = brandBlue),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.height(32.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                            ) {
                                Text("Proceed", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("$55.00", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cancellation Policy
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, brandBlue.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PriorityHigh, contentDescription = "Warning", tint = Color.Red, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cancellation Policy:", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Free cancellation up to 24 hours before the session. Cancellations within 24 hours are subject to a 50% fee.",
                        color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp, lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "By proceeding, you agree to our Terms of Service and Privacy Policy. Your payment will be held securely until the service is completed.",
                color = Color.Gray, fontSize = 10.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(subtitle, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookingCheckoutScreenPreview() {
    BookingCheckoutScreen(
        onBackClick = {},
        onProceedClick = {}
    )
}