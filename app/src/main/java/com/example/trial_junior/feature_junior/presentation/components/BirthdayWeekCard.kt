package com.example.trial_junior.feature_junior.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Import sp for font size
import com.example.trial_junior.R

@Composable
fun WeekNavigator() {
    var currentDayIndex by remember { mutableStateOf(0) }
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Map to store images for each day
    val dayImages = mapOf(
        0 to listOf(R.drawable.profile, R.drawable.profile, R.drawable.icon, R.drawable.icon, R.drawable.profile, R.drawable.profile), // Monday
        1 to listOf(R.drawable.icon, R.drawable.icon, R.drawable.profile, R.drawable.profile, R.drawable.icon, R.drawable.icon), // Tuesday
        2 to listOf(R.drawable.profile, R.drawable.icon, R.drawable.profile, R.drawable.icon, R.drawable.profile, R.drawable.icon), // Wednesday
        3 to listOf(R.drawable.icon, R.drawable.profile, R.drawable.icon, R.drawable.profile, R.drawable.icon, R.drawable.profile), // Thursday
        4 to listOf(R.drawable.profile, R.drawable.profile, R.drawable.icon, R.drawable.icon, R.drawable.profile, R.drawable.profile), // Friday
        5 to listOf(R.drawable.icon, R.drawable.icon, R.drawable.profile, R.drawable.profile, R.drawable.icon, R.drawable.icon), // Saturday
        6 to listOf(R.drawable.profile, R.drawable.icon, R.drawable.profile, R.drawable.icon, R.drawable.profile, R.drawable.icon)  // Sunday
    )

    // Get the images for the current day, default to an empty list if not found
    val currentDayImageList = dayImages[currentDayIndex] ?: emptyList()

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Column(){
                        Text(
                            text = days[currentDayIndex],
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(text="Birthday on " + days[currentDayIndex],
                            fontSize = 17.sp)
                    }

                }
                Box() {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (currentDayIndex > 0) {
                            Button(
                                onClick = { currentDayIndex-- },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFFFFF))
                            ) {
                                Text("<", color = Color.Black, fontSize = 30.sp)
                            }
                        }

                        if (currentDayIndex < days.size - 1) {
                            Button(
                                onClick = { currentDayIndex++ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFFFFF))
                            ) {
                                Text(">", color = Color.Black, fontSize = 30.sp)
                            }
                        }
                    }
                }
            }

            // Pass the current day's images to ImageCardNavigator
            ImageCardNavigator(images = currentDayImageList)
        }
    }
}


@Composable
fun ImageCardNavigator(images: List<Int>) { // Accept list of image resources as parameter
    var currentIndex by remember { mutableStateOf(0) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Display the first image if available
                if (currentIndex < images.size) {
                    Box( // Wrap each image in a Box
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(images[currentIndex]),
                            contentDescription = "Image 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(145.dp)
                        )
                    }
                }

                // Display the second image if available
                if (currentIndex + 1 < images.size) {
                    Box( // Wrap each image in a Box
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(images[currentIndex + 1]),
                            contentDescription = "Image 2",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(145.dp)
                        )
                    }
                }
            }

            // Buttons on the right, stacked vertically
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {

                if (currentIndex > 0) {
                    Button(
                        onClick = { currentIndex -= 2 },
                        modifier = Modifier.padding(bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFFFFF))
                    ) {
                        Text("<", color = Color.Black, fontSize = 30.sp)
                    }
                }


                if (currentIndex + 2 < images.size) {
                    Button(
                        onClick = { currentIndex += 2 },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFFFFF))
                    ) {
                        Text(">", color = Color.Black, fontSize = 30.sp)
                    }
                }
            }
        }
    }
}

