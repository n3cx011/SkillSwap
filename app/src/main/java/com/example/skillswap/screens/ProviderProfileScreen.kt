package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skillswap.R

@Composable
fun ProviderProfileScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit = {}
) {
    val backgroundDark = Color(0xFF141414)
    val brandBlue = Color(0xFF2E5BCC)
    val buttonYellow = Color(0xFFFFD500)

    Scaffold(
        topBar = { ProviderTopBar(onBackClick = onBackClick) },
        bottomBar = { HomeBottomBar(navController = navController, currentRoute = "provider_profile") }, // Reusing your existing bottom bar
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Provider Profile",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )

            // Overlapping Cover & Avatar Layout
            Box(modifier = Modifier.fillMaxWidth()) {
                // 1. Cover Image
                Image(
                    painter = painterResource(id = R.drawable.img_guitar_cover), // Ensure you exported this!
                    contentDescription = "Cover Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop
                )

                // 2. Avatar & Stats Row (Offset to overlap the cover)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp, start = 20.dp, end = 20.dp) // Pushes it down so it hangs over the edge
                ) {
                    // Profile Image with Verification Badge
                    Box(modifier = Modifier.size(120.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar_robbie),
                            contentDescription = "Robbie Harrison",
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .border(4.dp, backgroundDark, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        // Verified Checkmark Box
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = (-16).dp, y = (-12).dp)
                                .size(24.dp)
                                .background(Color(0xFF1E59C9), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Check, contentDescription = "Verified", tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    }

                    // Stats List
                    Column(
                        modifier = Modifier
                            .padding(top = 70.dp, start = 8.dp) // Aligns stats below the cover image line
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatRow(icon = Icons.Outlined.Person, text = "Guitar Teacher Jobs\nCompleted")
                        StatRow(icon = Icons.Outlined.Layers, text = "5+ Years Experience")
                        StatRow(icon = Icons.Outlined.Attachment, text = "Avg.Response Time-1\nhour")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name & Title
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("Robbie Harrison", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Professional Guitarist &\nTeacher", color = Color.Gray, fontSize = 14.sp, lineHeight = 20.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // About Me Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(containerColor = brandBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("About Me", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "I'm Robbie, a professional guitarist and music teacher with over 5 years of experience. I teach students of ages and skill levels, focusing on both classical and modern guitar techniques.",
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Next Button
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Next", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// Custom Top Bar to match the design's unique search layout
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderTopBar(onBackClick: () -> Unit={}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // SkillSwap Logo Text
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) { append("SKILL") }
                withStyle(style = SpanStyle(color = Color(0xFF29B6F6))) { append("SWAP") }
            },
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Search Box
        Box(
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Black, modifier = Modifier.padding(end = 12.dp))
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Mic Icon
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Mic, contentDescription = "Voice", tint = Color.Black, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun StatRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp).offset(y = 2.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.White, fontSize = 12.sp, lineHeight = 16.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProviderProfileScreenPreview() {
    ProviderProfileScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        onBackClick = {})
}