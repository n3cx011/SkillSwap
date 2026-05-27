package com.example.skillswap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailabilityCalendarScreen(navController: NavController, onBackClick: () -> Unit = {}) {
    val backgroundDark = Color(0xFF141414)
    val cardBlue = Color(0xFF325ECD)
    val buttonYellow = Color(0xFFE5D54C)
    val gridBackground = Color(0xFFE0E0E0)
    val borderGray = Color(0xFF9E9E9E)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { }, // Empty to place title in the body for better alignment
                actions = {
                    IconButton(onClick = { /* Handle More */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundDark)
            )
        },
        bottomBar = { HomeBottomBar(navController = navController, currentRoute = "availability_calendar") }, // Reusing the bottom bar
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Availability Calender",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 1. The Calendar Grid Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(gridBackground)
                    .border(1.dp, borderGray, RoundedCornerShape(8.dp))
            ) {
                // Calendar Header
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("April 28 - May 4,2025", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("April 28", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "To", tint = Color.Black, modifier = Modifier.size(14.dp).padding(horizontal = 2.dp))
                        Text("May 03", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }

                HorizontalDivider(color = borderGray)

                // Calendar Table
                Row(modifier = Modifier.fillMaxWidth()) {
                    CalendarColumn(day = "Mon", date = "28", modifier = Modifier.weight(1f), slots = listOf("A", "A", "A", "B", "A", "A"), borderGray = borderGray)
                    CalendarColumn(day = "Tue", date = "29", modifier = Modifier.weight(1f), slots = listOf("", "A", "A", "", "A", ""), borderGray = borderGray)
                    CalendarColumn(day = "Wen", date = "30", modifier = Modifier.weight(1f), slots = listOf("", "B", "A", "", "B", ""), borderGray = borderGray)
                    CalendarColumn(day = "Thus", date = "31", modifier = Modifier.weight(1f), slots = listOf("", "", "", "A", "", ""), borderGray = borderGray)
                    CalendarColumn(day = "Fri", date = "02", modifier = Modifier.weight(1f), slots = listOf("", "", "", "", "B", ""), borderGray = borderGray)
                    CalendarColumn(day = "Sat", date = "03", modifier = Modifier.weight(1f), slots = listOf("", "", "B", "", "A", ""), borderGray = borderGray)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Selected Times Card (Blue)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Available Row
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("02.00 PM - 03.00 PM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        StatusPill(text = "Available", dotColor = buttonYellow)
                    }
                    // Blocked Row
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("02.00 PM - 03.00 PM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        StatusPill(text = "Blocked", dotColor = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Mark Button
            Button(
                onClick = { /* Handle Mark */ },
                colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(200.dp).height(40.dp)
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Mark", tint = Color(0xFFA12020), modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Mark", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { /* Mark Blocked */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B8BF5)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(40.dp)
                ) {
                    Text("Mark as Blocked", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Button(
                    onClick = { /* Mark Available */ },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(40.dp)
                ) {
                    Text("Mark as Available", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// Helper: Column for the Calendar Table
@Composable
fun CalendarColumn(day: String, date: String, modifier: Modifier, slots: List<String>, borderGray: Color) {
    val times = listOf("06:00 AM", "09:00 AM", "11:00 AM", "02:00 PM", "04:00 PM", "06:00 PM")

    Column(modifier = modifier.border(0.5.dp, borderGray)) {
        // Header Cell
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF9E9E9E)).padding(vertical = 8.dp).border(0.5.dp, borderGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(day, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Text(date, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }

        // Time Slots
        slots.forEachIndexed { index, status ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(0.5.dp, borderGray),
                contentAlignment = Alignment.Center
            ) {
                if (status == "A") {
                    TimePill(time = times[index], isAvailable = true)
                } else if (status == "B") {
                    TimePill(time = times[index], isAvailable = false)
                }
            }
        }
    }
}

// Helper: Tiny pill inside the calendar cell
@Composable
fun TimePill(time: String, isAvailable: Boolean) {
    val color = if (isAvailable) Color(0xFF325ECD) else Color.Gray
    Box(
        modifier = Modifier
            .border(1.dp, color, RoundedCornerShape(4.dp))
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .background(color.copy(alpha = 0.2f), RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(time, color = color, fontSize = 7.sp, fontWeight = FontWeight.Bold, maxLines = 1)
    }
}

// Helper: Status pill in the blue card
@Composable
fun StatusPill(text: String, dotColor: Color) {
    Row(
        modifier = Modifier
            .background(Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(10.dp).background(dotColor, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AvailabilityCalendarScreenPreview() {
    AvailabilityCalendarScreen(
    navController = androidx.navigation.compose.rememberNavController())
}