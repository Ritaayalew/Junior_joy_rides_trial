package com.example.trial_junior.feature_junior.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trial_junior.feature_junior.presentation.components.SharedDashboardComponents
import com.example.trial_junior.feature_junior.presentation.components.StatisticCards


@Composable
fun AdminDashboardScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf("Interview Management") }


    Column(modifier = modifier) {
        // Shared components: "Admin Dashboard" text and time range selector
        SharedDashboardComponents(
            onTimeRangeSelected = { range ->
                // Handle time range selection if needed
            }
        )
        // Navigation section: "Birthday Management" and "Interview Management"
        ManagementSection { selectedSection ->
            currentScreen = selectedSection
            when (selectedSection) {
                "Interview Management" -> navController.navigate("interviewManagement") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
                "Birthday Management" -> navController.navigate("birthdayManagement") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Take remaining space
        ) {
            item{

            }
            // StatisticCards
            item {
                StatisticCards(
                    usersCount = 1234,
                    eventsCount = 156,
                    showsCount = 89,
                    birthdaysCount = 45
                )
            }
            // Weekly Activities Report
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Weekly Activities Report", style = MaterialTheme.typography.titleLarge)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Bar Chart Placeholder")
                        }
                    }
                }
            }
            // NavHost content
            item {
                NavHost(navController = navController, startDestination = "interviewManagement") {
                    composable("interviewManagement") {
                        InterviewManagementScreen(Modifier)
                    }
                    composable("birthdayManagement") {
                        BirthdayManagementScreen(Modifier)
                    }
                }
            }
            // Add some padding at the bottom for better scrolling experience
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
}   }


@Composable
fun ManagementSection(onSectionSelected: (String) -> Unit = {}) {
    var selected by remember { mutableStateOf("") } // Track which item is selected

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Add side padding for layout
        horizontalArrangement = Arrangement.SpaceEvenly // Distribute items evenly
    ) {
        // Birthday Management
        ManagementItem(
            title = "Birthday Management",
            isSelected = selected == "Birthday Management",
            onSectionSelected = { newSelection ->
                selected = newSelection
                onSectionSelected(newSelection) // Ensure external callback is updated
            }
        )

        // Interview Management
        ManagementItem(
            title = "Interview Management",
            isSelected = selected == "Interview Management",
            onSectionSelected = { newSelection ->
                selected = newSelection
                onSectionSelected(newSelection) // Ensure external callback is updated
            }
        )
    }
}

@Composable
fun ManagementItem(title: String, isSelected: Boolean, onSectionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onSectionSelected(title) } // Correctly updates selection state externally
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        title.split(" ").forEach { word ->
            Text(
                text = word,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color(0xFF615517) else MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(
            modifier = Modifier
                .width(170.dp)
                .height(8.dp)
                .background(if (isSelected) Color(0xFF615517) else Color(0xFFD9D9D9))
        )
    }
}

