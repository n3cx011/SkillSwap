package com.example.skillswap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookingConfirmationScreen(onBackToHomeClick: () -> Unit) {
    val backgroundDark = Color(0xFF141414)
    val cardGray = Color(0xFF424242)
    val brandBlue = Color(0xFF1E59C9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Success Icon
        Icon(Icons.Default.Verified, contentDescription = "Success", tint = Color(0xFF2979FF), modifier = Modifier.size(80.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Text("Booking Confirmed!", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "You're all set! We've notified Robbie Harrison. They will meet you tomorrow at 2:00 PM.",
            color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp, textAlign = TextAlign.Center, lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Receipt Card
        Card(
            colors = CardDefaults.cardColors(containerColor = cardGray),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Order ID", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Text("ORD-8D7OXD3V", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                Spacer(modifier = Modifier.height(24.dp))

                ReceiptRow("Service", "Guitar Lessons")
                Spacer(modifier = Modifier.height(8.dp))
                ReceiptRow("Provider", "Robbie Harrison")
                Spacer(modifier = Modifier.height(8.dp))
                ReceiptRow("Date & Time", "Jan 14, 2:00 PM")
                Spacer(modifier = Modifier.height(8.dp))
                ReceiptRow("Duration", "60 minutes")
                Spacer(modifier = Modifier.height(8.dp))
                ReceiptRow("Location", "Music Studio Downtown")

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Total Paid", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text("$55.00", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Button(
            onClick = { }, modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E9E9E)), shape = RoundedCornerShape(24.dp)
        ) {
            Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save to Calendar", color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { }, modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E9E9E)), shape = RoundedCornerShape(24.dp)
        ) {
            Icon(Icons.Default.Link, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Share Booking Details", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // What's Next
        Box(
            modifier = Modifier.fillMaxWidth().border(1.dp, brandBlue.copy(alpha = 0.5f), RoundedCornerShape(16.dp)).padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.NotificationsActive, contentDescription = "Next", tint = Color.White, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("What's Next?", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("We've sent you a confirmation email. Robbie will contact you if there are any changes. You can message them anytime from your chat dashboard.", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp, lineHeight = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Back to Home
        Button(
            onClick = onBackToHomeClick, modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = brandBlue), shape = RoundedCornerShape(28.dp)
        ) {
            Text("Back to Home", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ReceiptRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp, modifier = Modifier.weight(1f))
        Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.weight(1.5f), textAlign = TextAlign.End)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookingConfirmationScreenPreview() {
    BookingConfirmationScreen(
        onBackToHomeClick = {}
    )
}