package com.example.skillswap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningsScreen(onBackClick: () -> Unit) {
    val backgroundDark = Color(0xFF141414)
    val cardBlack = Color.Black
    val brandBlue = Color(0xFF1E59C9)
    val buttonYellow = Color(0xFFFFD500)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Earnings",
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
                actions = { Spacer(modifier = Modifier.width(48.dp)) }, // Balances the back button
                colors = TopAppBarDefaults.topAppBarColors(containerColor = cardBlack)
            )
        },
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // 1. Total Earnings Header
            Text(text = "Earnings", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$1,250.00", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(40.dp))

            // 2. Stats Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // January Earnings Card
                Card(
                    modifier = Modifier.weight(1f).height(100.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBlack),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("January\nEarnings", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("$800", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Total Jobs Card (With Blue Border)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(2.dp, brandBlue, RoundedCornerShape(8.dp)),
                    colors = CardDefaults.cardColors(containerColor = cardBlack),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Total Jobs", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("20", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Custom Bar Chart Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0A0A0A), RoundedCornerShape(8.dp)) // Very dark background for chart
                    .padding(16.dp)
            ) {
                Column {
                    // Chart Grid and Bars
                    Row(
                        modifier = Modifier.fillMaxWidth().height(180.dp)
                    ) {
                        // Y-Axis Labels
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("$10,000", color = Color.Gray, fontSize = 10.sp)
                            Text("$5,000", color = Color.Gray, fontSize = 10.sp)
                            Text("$0", color = Color.Gray, fontSize = 10.sp)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // X-Axis Bars
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            // Using a helper function to draw the simulated bar groups
                            BarGroup(blueHeight = 90.dp, grayHeight = 110.dp)
                            BarGroup(blueHeight = 130.dp, grayHeight = 160.dp)
                            BarGroup(blueHeight = 100.dp, grayHeight = 120.dp)
                            BarGroup(blueHeight = 120.dp, grayHeight = 140.dp)
                            BarGroup(blueHeight = 120.dp, grayHeight = 150.dp)
                            BarGroup(blueHeight = 100.dp, grayHeight = 130.dp)
                            BarGroup(blueHeight = 100.dp, grayHeight = 130.dp)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // X-Axis Labels
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 45.dp), // Offset to match bars
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Monday", color = Color.Gray, fontSize = 10.sp)
                        Text("Wednesday", color = Color.Gray, fontSize = 10.sp)
                        Text("Friday", color = Color.Gray, fontSize = 10.sp)
                        Text("Sunday", color = Color.Gray, fontSize = 10.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // 4. Withdrawal Button
            Button(
                onClick = { /* Handle Withdrawal */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Withdrawal to Bank",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// Helper composable to draw the double-bars in the chart
@Composable
fun BarGroup(blueHeight: androidx.compose.ui.unit.Dp, grayHeight: androidx.compose.ui.unit.Dp) {
    val brandBlue = Color(0xFF1E59C9)
    val barGray = Color(0xFF78909C) // Blue-gray color from your design

    Row(verticalAlignment = Alignment.Bottom) {
        Box(modifier = Modifier.width(12.dp).height(blueHeight).background(brandBlue))
        Box(modifier = Modifier.width(12.dp).height(grayHeight).background(barGray))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EarningsScreenPreview() {
    EarningsScreen(onBackClick = {})
}