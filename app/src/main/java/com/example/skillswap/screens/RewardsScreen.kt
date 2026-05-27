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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(onBackClick: () -> Unit) {
    val brandBlue = Color(0xFF1E59C9)
    val darkCardColor = Color(0xFF141414)
    val darkerButton = Color(0xFF0A0A0A)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { }, // Empty title to match design
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = brandBlue)
            )
        },
        bottomBar = { ProfileBottomBar() } // Reusing the bottom bar again!
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brandBlue) // Top half is blue
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // 1. Header Text
            Text(
                text = buildAnnotatedString {
                    append("Invite Friends to\nGet ")
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("$10")
                    }
                },
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            // 2. The Bottom Dark Card
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(darkCardColor)
                    .padding(32.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = buildAnnotatedString {
                            append("Give $10, Get ")
                            withStyle(style = SpanStyle(color = brandBlue)) {
                                append("$10")
                            }
                        },
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Invite your friends to SkillSwap and you will both get $10 when they book their service",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // 3. Referral Code Box
                    Row(
                        modifier = Modifier
                            .background(brandBlue, RoundedCornerShape(8.dp))
                            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "SKILL - 5221",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.White, RoundedCornerShape(6.dp))
                                .clickable { /* Copy to clipboard logic */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color.Black, modifier = Modifier.size(16.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    // 4. Social Share Icons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SocialIcon(R.drawable.ic_messenger) // Make sure you have these assets!
                        SocialIcon(R.drawable.ic_facebook)
                        SocialIcon(R.drawable.ic_whatsapp)
                        SocialIcon(R.drawable.ic_twitter)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // 5. Bottom Button
                    Button(
                        onClick = { /* Handle invite */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = darkerButton),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Invite More Friends", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun SocialIcon(iconRes: Int) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = "Share",
        modifier = Modifier
            .size(36.dp)
            .clickable { /* Handle Share */ }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RewardsScreenPreview() {
    RewardsScreen(onBackClick = {})
}