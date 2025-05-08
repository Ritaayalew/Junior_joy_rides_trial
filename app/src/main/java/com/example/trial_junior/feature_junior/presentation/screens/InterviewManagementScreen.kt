package com.example.trial_junior.feature_junior.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun InterviewManagementScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // Recent Basic Interviews
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Recent Basic Interviews", style = MaterialTheme.typography.titleLarge)
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Circle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                        Text("New Interview Booking", modifier = Modifier.padding(start = 8.dp))
                    }
                    Text(
                        "Sarah send basic Interview Request",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        "... Show More",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Circle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                        Text("New Interview Booking", modifier = Modifier.padding(start = 8.dp))
                    }
                    Text(
                        "Sophia send basic Interview Request",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        "... Show More",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        // Videos for Approval
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .shadow(4.dp, shape = RectangleShape)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Videos for Approval", style = MaterialTheme.typography.titleLarge)
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VideoCard(
                            name = "Elizabeth",
                            age = 9,
                            dateTime = "Sunday, 11:45AM",
                            isApprovedSection = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp, top = 16.dp, bottom = 8.dp)
                        )
                        VideoCard(
                            name = "Eliza",
                            age = 10,
                            dateTime = "Sunday, 09:45AM",
                            isApprovedSection = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VideoCard(
                            name = "John",
                            age = 8,
                            dateTime = "Sunday, 10:30AM",
                            isApprovedSection = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp, top = 8.dp, bottom = 16.dp)
                        )
                        VideoCard(
                            name = "Emma",
                            age = 11,
                            dateTime = "Sunday, 12:15PM",
                            isApprovedSection = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, top = 8.dp, bottom = 16.dp)
                        )
                    }
                    Button(
                        onClick = { /* Show More logic */ },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC5AE3D), // Gold color for Show More
                            contentColor = Color.White
                        )
                    ) {
                        Text(text="Show More")
                    }
                }
            }
        }

        // Videos that have been Approved
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .shadow(4.dp, shape = RectangleShape)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Videos that have been Approved", style = MaterialTheme.typography.titleLarge)
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VideoCard(
                            name = "Elizabeth",
                            age = 9,
                            contact = "Sat123@gmail.com",
                            isApprovedSection = true,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                        )
                        VideoCard(
                            name = "Bahran",
                            age = 8,
                            contact = "Sat123@gmail.com",
                            isApprovedSection = true,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        )
                    }
                    Button(
                        onClick = { /* Show More logic */ },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC5AE3D), // Gold color for Show More
                            contentColor = Color.White
                        )
                    ) {
                        Text(text="Show More")
                    }
                }
            }
        }
    }
}

@Composable
fun VideoCard(
    modifier: Modifier = Modifier,
    name: String,
    age: Int,
    dateTime: String? = null,
    contact: String? = null,
    isApprovedSection: Boolean
) {
    Card(
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(Icons.Default.Videocam, contentDescription = null)
            Text("Name: $name", modifier = Modifier.padding(top = 4.dp))
            Text("Age: $age", modifier = Modifier.padding(top = 4.dp))
            if (isApprovedSection) {
                contact?.let {
                    Text("Contact: $it", modifier = Modifier.padding(top = 4.dp))
                }
            } else {
                dateTime?.let {
                    Text("Date and time: $it", modifier = Modifier.padding(top = 4.dp))
                }
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button(
                        onClick = { /* Approve logic */ },
                        modifier = Modifier
                            .height(20.dp)
                            .width(70.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green for Approve
                    ) {
                        Text(
                            "Approve",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Cancel logic */ },
                        modifier = Modifier
                            .height(20.dp)
                            .width(75.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)) // Red for Cancel
                    ) {
                        Text(
                            "Cancel",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun InterviewManagementScreenPreview() {
    InterviewManagementScreen()
}