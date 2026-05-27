package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R
import androidx.navigation.NavController

// Blueprint for a single review
data class ReviewData(
    val name: String,
    val date: String,
    val text: String,
    val avatarRes: Int,
    val rating: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingsReviewsScreen(navController: NavController, onBackClick: () -> Unit) {
    val backgroundDark = Color(0xFF141414)
    val brandBlue = Color(0xFF3D63D2)
    val buttonYellow = Color(0xFFFFD500)
    val textBlue = Color(0xFF4C82F6)

    val reviews = listOf(
        ReviewData("Emily Heris", "May 19,2025", "Very friendly and professional teacher. The lessons are engaging and motivating. Would love more advanced theory sessions.", R.drawable.avatar_emily, 5),
        ReviewData("Kevin Medar", "Sep 12 2025", "Great guitar teacher. Easy to understand and very helpful", R.drawable.avatar_kevin, 5),
        ReviewData("Megan Sarah", "January, 08,2026", "Thank you for your lovely lessons. Lessons are very clear and fun. Highly recommended.", R.drawable.avatar_megan, 5)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { }, // Empty title to keep it clean, we'll put the big title in the body
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundDark)
            )
        },
        bottomBar = { HomeBottomBar(navController = navController, currentRoute = "ratings_reviews") }, // Reusing the bottom bar
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            // 1. Header Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Ratings and Reviews", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.MoreVert, contentDescription = "More Options", tint = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Ratings Title & Write Review Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Ratings", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Button(
                    onClick = { /* Handle Write Review */ },
                    colors = ButtonDefaults.buttonColors(containerColor = brandBlue),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Write a Review", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Rating Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = brandBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Big Score & Stars
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("4.9 /5", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(12.dp))
                        Row {
                            repeat(4) { Icon(Icons.Default.Star, contentDescription = null, tint = buttonYellow, modifier = Modifier.size(16.dp)) }
                            Icon(Icons.AutoMirrored.Filled.StarHalf, contentDescription = null, tint = buttonYellow, modifier = Modifier.size(16.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Progress Bars
                    RatingBarRow(star = 5, progress = 0.8f)
                    RatingBarRow(star = 4, progress = 0.6f)
                    RatingBarRow(star = 3, progress = 0.3f)
                    RatingBarRow(star = 2, progress = 0.1f)
                    RatingBarRow(star = 1, progress = 0.8f) // Matching the unique design pattern
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Reviews", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))

            // 4. Scrollable Reviews List & Bottom Action Buttons
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(bottom = 100.dp) // Gives room for the bottom buttons to scroll past
                ) {
                    items(reviews) { review ->
                        ReviewItem(review = review, textBlue = textBlue, buttonYellow = buttonYellow)
                    }
                }

                // Floating Action Buttons at the bottom of the list
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { /* Save Draft */ },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = brandBlue),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Save Draft", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { /* Publish */ },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Publish Listing", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// Helper Composable for the progress bars
@Composable
fun RatingBarRow(star: Int, progress: Float) {
    val buttonYellow = Color(0xFFFFD500)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Text("$star", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(12.dp), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.width(4.dp))
        Icon(Icons.Default.Star, contentDescription = null, tint = buttonYellow, modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(12.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.weight(1f).height(6.dp).clip(CircleShape),
            color = buttonYellow,
            trackColor = Color.White.copy(alpha = 0.3f),
        )
    }
}

// Helper Composable for Individual Review Cards
@Composable
fun ReviewItem(review: ReviewData, textBlue: Color, buttonYellow: Color) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        // Avatar
        Image(
            painter = painterResource(id = review.avatarRes),
            contentDescription = review.name,
            modifier = Modifier.size(48.dp).clip(CircleShape).background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Review Details
        Column(modifier = Modifier.weight(1f)) {
            Text(review.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Responded", color = textBlue, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(16.dp))
                Row {
                    repeat(review.rating) { Icon(Icons.Default.Star, contentDescription = null, tint = buttonYellow, modifier = Modifier.size(10.dp)) }
                }
                Spacer(modifier = Modifier.weight(1f)) // Pushes the date to the far right
                Text(review.date, color = Color.Gray, fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = review.text,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                lineHeight = 18.sp
            )

            // Heart Icon at the bottom right of the text
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Icon(Icons.Default.Favorite, contentDescription = "Like", tint = Color.Red, modifier = Modifier.size(14.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RatingsReviewsScreenPreview() {
    RatingsReviewsScreen(navController = androidx.navigation.compose.rememberNavController(),
        onBackClick = {})
}