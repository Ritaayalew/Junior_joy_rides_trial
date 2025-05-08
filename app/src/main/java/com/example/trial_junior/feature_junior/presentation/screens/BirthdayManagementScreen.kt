package com.example.trial_junior.feature_junior.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Download
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun BirthdayManagementScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
//        SharedDashboardComponents(
//            onTimeRangeSelected = { range ->
//                // Handle time range selection if needed
//            }
//        )
        // Recent Invitations
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Recent Invitations", style = MaterialTheme.typography.titleLarge)
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Circle, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                        Text("New Birthday Invitation", modifier = Modifier.padding(start = 8.dp))
                    }
                    Text("Sarah booked a birthday celebration", modifier = Modifier.padding(top = 4.dp))
                    Text("... Show More", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 4.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Circle, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                        Text("New Birthday Invitation", modifier = Modifier.padding(start = 8.dp))
                    }
                    Text("Alex booked a birthday celebration", modifier = Modifier.padding(top = 4.dp))
                    Text("... Show More", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
        // Next Week Wishlist
        Card(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Next Week Wishlist", style = MaterialTheme.typography.titleLarge)
                Column {
                    Card(modifier = Modifier.padding(vertical = 8.dp)) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text("Monday 🎉")
                            Text("Alex, Sophia, Liam")
                        }
                    }
                }
            }
        }
    }
}

// Data class to hold birthday person details
data class BirthdayPerson(
    val name: String,
    val imageUrl: String
)

@Preview
@Composable
fun BirthdayManagementScreenPreview() {
    BirthdayManagementScreen()
}