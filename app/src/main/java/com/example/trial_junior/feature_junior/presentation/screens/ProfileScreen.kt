package com.example.trial_junior.feature_junior.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.trial_junior.R
import com.example.trial_junior.feature_junior.domain.model.InvitationItem
import com.example.trial_junior.feature_junior.domain.model.BasicInterviewItem
import com.example.trial_junior.feature_junior.domain.model.SpecialInterviewItem
import com.example.trial_junior.feature_junior.domain.model.WishListItem
import com.example.trial_junior.feature_junior.presentation.viewModels.UserViewModel
import androidx.compose.runtime.LaunchedEffect
import com.example.trial_junior.feature_junior.presentation.util.Screen

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: UserViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val user = state.user

    LaunchedEffect(Unit) {
        viewModel.getMyProfile()
    }

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .absoluteOffset(x = 120.dp, y = 107.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = user?.let { "${it.firstName} ${it.lastName}" } ?: "Full Name",
                fontSize = 30.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = user?.email ?: "name@gmail.com",
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.navigate(Screen.EditProfileScreen.route)},
                Modifier
                    .width(201.dp)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBEBEBE)
                )
            ) {
                Text("Edit profile", fontSize = 20.sp, style = MaterialTheme.typography.headlineMedium)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "My Application",
                fontSize = 32.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Cards for invitations, basicInterviews, specialInterviews, and wishLists
        items((user?.invitations ?: emptyList()) + (user?.basicInterviews ?: emptyList()) +
                (user?.specialInterviews ?: emptyList()) + (user?.wishLists ?: emptyList())) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .shadow(elevation = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F8F8))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    when (item) {
                        is InvitationItem -> {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Invitation: ${item.childName}", fontSize = 18.sp, color = Color.Black)
                                Button(
                                    onClick = { /* Handle button click */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFF9A927)
                                    )
                                ) {
                                    Text("PENDING")
                                }
                            }
                            Text(text = "Date: ${item.date}", fontSize = 12.sp, color = Color.Black)
                            Text(text = item.specialRequests ?: "", fontSize = 12.sp, color = Color.Black)
                        }
                        is BasicInterviewItem -> {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Basic Interview", fontSize = 18.sp, color = Color.Black)
                                Button(
                                    onClick = { /* Handle button click */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF4B951F)
                                    )
                                ) {
                                    Text("APPROVED")
                                }
                            }
                            Text(text = "Date: ${item.guardianEmail}", fontSize = 12.sp, color = Color.Black)
                            Text(text = item.guardianName ?: "", fontSize = 12.sp, color = Color.Black)
                        }
                        is SpecialInterviewItem -> {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Special Interview", fontSize = 18.sp, color = Color.Black)
                                Button(
                                    onClick = { /* Handle button click */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF4B951F)
                                    )
                                ) {
                                    Text("APPROVED")
                                }
                            }
                            Text(text = "Date: ${item.guardianName}", fontSize = 12.sp, color = Color.Black)
                            Text(text = item.guardianEmail ?: "", fontSize = 12.sp, color = Color.Black)
                        }
                        is WishListItem -> {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Wish List: ${item.childName}", fontSize = 18.sp, color = Color.Black)
                                Button(
                                    onClick = { /* Handle button click */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFF9A927)
                                    )
                                ) {
                                    Text("PENDING")
                                }
                            }
                            Text(text = "Date: ${item.date}", fontSize = 12.sp, color = Color.Black)
                            Text(text = item.imageUrl ?: "", fontSize = 12.sp, color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { /* Handle button click */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF9F8F8)
                            )
                        ) {
                            Text("Edit", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { /* Handle button click */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF9F8F8)
                            )
                        ) {
                            Text("Cancel", color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun LaunchedEffect(key: Any, block: suspend () -> Unit) {
    androidx.compose.runtime.LaunchedEffect(key) {
        block()
    }
}