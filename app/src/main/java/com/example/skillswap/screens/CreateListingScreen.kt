package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R

@Composable
fun CreateListingScreen(
    onBackClick: () -> Unit,
    onSaveDraftClick: () -> Unit = {},
    onPublishClick: () -> Unit = {}
) {
    val backgroundDark = Color(0xFF141414)
    val inputBackground = Color(0xFFE0E0E0)
    val brandBlue = Color(0xFF2E5BCC)
    val buttonYellow = Color(0xFFFFD500)

    // States for form inputs
    var serviceTitle by remember { mutableStateOf("Guitar Lessons") }
    var isFixedPrice by remember { mutableStateOf(true) } // true = Fixed, false = Hourly

    Scaffold(
        topBar = { SellerTopBar(onBackClick) },
        bottomBar = { ProfileBottomBar() }, // Reusing the profile bottom bar
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text("Create New Listing", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            // 1. Service Title Input
            Text("Service Title", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(inputBackground, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = serviceTitle,
                    onValueChange = { serviceTitle = it },
                    textStyle = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Pricing Toggle (Labeled 'Description' in design)
            Text("Description", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(inputBackground, RoundedCornerShape(8.dp))
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pricing",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 12.dp).weight(0.5f)
                )

                // Toggle Container
                Row(modifier = Modifier.weight(1f)) {
                    // Fixed Price Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (isFixedPrice) Color(0xFF2C2C2C) else Color.Transparent,
                                RoundedCornerShape(6.dp)
                            )
                            .clickable { isFixedPrice = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isFixedPrice) {
                                Box(modifier = Modifier.size(10.dp).background(buttonYellow, CircleShape))
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text("Fixed Price", color = if (isFixedPrice) Color.Gray else Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Hourly Rate Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (!isFixedPrice) Color(0xFF2C2C2C) else Color.Transparent,
                                RoundedCornerShape(6.dp)
                            )
                            .clickable { isFixedPrice = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (!isFixedPrice) {
                                Box(modifier = Modifier.size(10.dp).background(buttonYellow, CircleShape))
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text("Hourly Rate", color = if (!isFixedPrice) Color.Gray else Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Image Uploader Section
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Image Uploader", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("1/2", color = Color.White, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 2x2 Image Grid
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    UploadImagePreview(R.drawable.img_upload_1, Modifier.weight(1f))
                    UploadImagePreview(R.drawable.img_upload_2, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    UploadImagePreview(R.drawable.img_upload_3, Modifier.weight(1f))
                    UploadImagePreview(R.drawable.img_upload_4, Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add Photos Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(inputBackground, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Image, contentDescription = "Gallery", tint = Color.Black, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("4 + 12 Photos", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .background(Color(0xFFBDBDBD), RoundedCornerShape(6.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .clickable { /* Handle Add Photos */ }
                ) {
                    Text("+Add 12 Photos", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 4. Bottom Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onSaveDraftClick,
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = brandBlue),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Save Draft", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onPublishClick,
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Publish Listing", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// Custom Top Bar with Back Arrow + Search + Mic
@Composable
fun SellerTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
        }

        Spacer(modifier = Modifier.width(8.dp))

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
fun UploadImagePreview(imageRes: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f) // Makes it a perfect square
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Uploaded Photo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateListingScreenPreview() {
    CreateListingScreen(onBackClick = {})
}