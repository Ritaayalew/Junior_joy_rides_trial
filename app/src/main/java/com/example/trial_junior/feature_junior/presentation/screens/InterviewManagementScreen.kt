package com.example.trial_junior.feature_junior.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trial_junior.feature_junior.domain.model.BasicInterviewItem
import com.example.trial_junior.feature_junior.presentation.viewModels.BasicInterviewListViewModel

@Composable
fun InterviewManagementScreen(
    modifier: Modifier = Modifier,
    basicViewModel: BasicInterviewListViewModel = hiltViewModel()
) {
    val state by basicViewModel.state

    LaunchedEffect(Unit) {
        basicViewModel.getItems()
    }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {

        // --- Upcoming Interviews ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Upcoming Interviews", style = MaterialTheme.typography.titleLarge)

                when {
                    state.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)
                        )
                    }

                    state.error != null -> {
                        Text(
                            text = state.error ?: "An error occurred",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    else -> {
                        val itemsToDisplay = state.items.filter { it.upcoming && !it.approved }

                        if (itemsToDisplay.isEmpty()) {
                            Text("No upcoming interviews available", modifier = Modifier.padding(top = 8.dp))
                        } else {
                            itemsToDisplay.forEach { interview ->
                                BasicInterviewItemRow(interview)
                                Divider(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }

        // --- Videos for Approval ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
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
                            name = "sissy",
                            age = 11,
                            dateTime = "Sunday, 12:15PM",
                            isApprovedSection = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, top = 8.dp, bottom = 16.dp)
                        )
                    }

                    Button(
                        onClick = { /* TODO: Show More logic */ },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Show More")
                    }
                }
            }
        }

        // --- Approved Videos ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
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
                        onClick = { /* TODO: Show More logic */ },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Show More")
                    }
                }
            }
        }
    }
}

@Composable
fun BasicInterviewItemRow(interview: BasicInterviewItem) {
    val errorColor = MaterialTheme.colorScheme.error
    val statusText = if (interview.upcoming) "Status: Upcoming" else "Status: Completed"
    val approvedText = if (interview.approved) "Approved Interview" else "New Interview Booking"
    val approvedColor = if (interview.approved) Color.Green else errorColor

    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Circle, contentDescription = null, tint = approvedColor)
            Text(
                text = approvedText,
                modifier = Modifier.padding(start = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = "${interview.childName} (Age: ${interview.age})",
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = "Guardian: ${interview.guardianName}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(text = "Contact: ${interview.guardianPhone}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(text = "Email: ${interview.guardianEmail}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        if (interview.specialRequests.isNotEmpty()) {
            Text(
                text = "Special Requests: ${interview.specialRequests}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(text = statusText, maxLines = 1, overflow = TextOverflow.Ellipsis)
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
        modifier = modifier.shadow(4.dp, shape = RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(Icons.Default.Videocam, contentDescription = null)
            Text("Name: $name", modifier = Modifier.padding(top = 4.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("Age: $age", modifier = Modifier.padding(top = 4.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)

            if (isApprovedSection) {
                contact?.let {
                    Text("Contact: $it", modifier = Modifier.padding(top = 4.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            } else {
                dateTime?.let {
                    Text("Date and time: $it", modifier = Modifier.padding(top = 4.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button(
                        onClick = { /* Approve logic */ },
                        modifier = Modifier.height(20.dp).width(70.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Approve", style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Cancel logic */ },
                        modifier = Modifier.height(20.dp).width(75.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
                    ) {
                        Text("Cancel", style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InterviewManagementScreenPreview() {
    Surface {
        InterviewManagementScreen()
    }
}
