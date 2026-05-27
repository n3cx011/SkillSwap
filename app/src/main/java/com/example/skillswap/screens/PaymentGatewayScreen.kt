package com.example.skillswap.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
fun PaymentGatewayScreen(onBackClick: () -> Unit, onPayClick: () -> Unit) {
    val backgroundDark = Color(0xFF141414)
    val fieldGray = Color(0xFF8A8A8A)
    var saveCard by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment Gateway", color = Color.White, fontSize = 20.sp) },
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
            // Secure Badge
            Box(
                modifier = Modifier.fillMaxWidth().background(Color(0xFF8A8A8A), RoundedCornerShape(20.dp)).padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Lock, contentDescription = "Secure", tint = Color(0xFFFFD54F), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Secure SSL Encrypted Payment", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Choose Payment Method", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            // Payment Methods Row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                PaymentMethodBox(R.drawable.ic_gpay)
                PaymentMethodBox(R.drawable.ic_mastercard)
                PaymentMethodBox(R.drawable.ic_applepay)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Forms
            PaymentTextField(value = "Select Country", icon = Icons.Default.LocationOn, trailingIcon = Icons.Default.KeyboardArrowDown)
            Spacer(modifier = Modifier.height(16.dp))
            PaymentTextField(value = "Name on Card", icon = Icons.Default.Person)
            Spacer(modifier = Modifier.height(16.dp))
            PaymentTextField(value = "Card Number", icon = Icons.Default.CreditCard)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(modifier = Modifier.weight(1f)) { PaymentTextField(value = "Month/Year", icon = Icons.Default.CalendarToday) }
                Box(modifier = Modifier.weight(1f)) { PaymentTextField(value = "CVV", icon = Icons.Default.Lock) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = saveCard,
                    onCheckedChange = { saveCard = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Color(0xFF1E59C9))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save this Card", color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Pay Button
            Button(
                onClick = onPayClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = fieldGray),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Lock, contentDescription = "Secure", tint = Color(0xFFFFD54F), modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Pay $55.00 Securely", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Footer Info
            Box(
                modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xFF1E59C9).copy(alpha = 0.5f), RoundedCornerShape(16.dp)).padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Security, contentDescription = "Secure", tint = Color.White, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Secure Payment !", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Your payment information is encrypted and secure. We never store your card details. Powered by Stripe.", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp, lineHeight = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PaymentMethodBox(iconRes: Int) {
    Box(
        modifier = Modifier.size(width = 100.dp, height = 60.dp).background(Color.White, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = iconRes), contentDescription = "Payment Method", modifier = Modifier.height(30.dp), contentScale = ContentScale.Fit)
    }
}

@Composable
fun PaymentTextField(value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, trailingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    Box(
        modifier = Modifier.fillMaxWidth().height(56.dp).background(Color(0xFF8A8A8A), RoundedCornerShape(8.dp)).padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(value, color = Color.White, fontSize = 14.sp, modifier = Modifier.weight(1f))
            if (trailingIcon != null) {
                Icon(imageVector = trailingIcon, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentGatewayScreenPreview() {
    PaymentGatewayScreen(
        onBackClick = {},
        onPayClick = {}
    )
}