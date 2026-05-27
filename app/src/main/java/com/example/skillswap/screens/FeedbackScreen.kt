package com.example.skillswap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillswap.R

// Blueprint for a feedback item
data class FeedbackData(
    val name: String,
    val service: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(onBackClick: () -> Unit,
                   onLeaveReviewClick: () -> Unit = {}) {
    val backgroundDark = Color(0xFF141414)

    // Dummy data for the completed jobs
    val feedbackList = listOf(
        FeedbackData("Anna Smith", "Plumbing Repair", R.drawable.avatar_anna),
        FeedbackData("Alex Johnson", "Photography", R.drawable.avatar_alex),
        FeedbackData("Jayden Rodrigo", "Guitar Lessons", R.drawable.avatar_jayden),
        FeedbackData("Mark", "Guitar Lessons", R.drawable.avatar_mark) // Make sure you added avatar_mark
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Feedback",
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
                actions = { Spacer(modifier = Modifier.width(48.dp)) }, // Keeps title centered
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = { ProfileBottomBar() }, // Reusing the bottom bar from ProfileScreen
        containerColor = backgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(feedbackList) { feedback ->
                    FeedbackCard(feedback = feedback, onReviewClick = onLeaveReviewClick)
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
fun FeedbackCard(feedback: FeedbackData, onReviewClick: () -> Unit = {}) {
    val brandBlue = Color(0xFF1E59C9)
    val buttonYellow = Color(0xFFFFC107)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = brandBlue)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = feedback.imageRes),
                    contentDescription = feedback.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text and Button Column
            Column(modifier = Modifier.weight(1f)) {
                // Name and Heart Icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = feedback.name, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Save", tint = Color.Black)
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Service
                Text(text = feedback.service, color = Color.Black.copy(alpha = 0.6f), fontWeight = FontWeight.Bold, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(12.dp))

                // Review Button
                Button(
                    onClick = onReviewClick,
                    modifier = Modifier.height(32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonYellow),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                ) {
                    Text("Leave a Review", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeedbackScreenPreview() {
    FeedbackScreen(onBackClick = {})
}