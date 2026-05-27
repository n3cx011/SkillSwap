package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R

@Composable
fun OnboardingScreenTwo(
    onJoinNowClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    // The button blue color from your design
    val buttonBlue = Color(0xFF1E59C9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        // Top Image Section (Expands to fill available space)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                // Make sure to export the avatars as a single image group!
                painter = painterResource(id = R.drawable.onboarding_avatars),
                contentDescription = "Diverse 3D Avatars",
                modifier = Modifier.size(400.dp)
            )
        }

        // Bottom Content Section
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Let's Get\nStarted",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 52.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Unlock a world of limitless skills and knowledge with our free skill swapping app, where sharing is caring!",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Join Now Button
            Button(
                onClick = onJoinNowClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Join Now",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Link Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            // Extra bottom padding for safe area / gesture navigation bar
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreenTwoPreview(){
    OnboardingScreenTwo(
        onJoinNowClick = {},
        onLoginClick = {}
    )
}
