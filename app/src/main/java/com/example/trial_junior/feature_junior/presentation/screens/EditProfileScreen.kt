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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.trial_junior.R
import com.example.trial_junior.feature_junior.presentation.util.Screen
import com.example.trial_junior.feature_junior.presentation.viewModels.UserViewModel

@Composable
fun EditProfileScreen(navController: NavHostController, viewModel: UserViewModel = hiltViewModel()) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    // Pre-fill fields with user data when available
    LaunchedEffect(state.user) {
        state.user?.let {
            firstName = it.firstName
            lastName = it.lastName
            email = it.email
        }
    }

    // Fetch profile data on screen load
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
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            if (state.error != null) {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
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
                text = "Full Name",
                fontSize = 30.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = email.ifEmpty { "name@gmail.com" },
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 40.dp,
                        vertical = 8.dp
                    )
                    .shadow(elevation = 10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F8F8))
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "First Name",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("Enter First Name") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE5E4E4),
                        unfocusedContainerColor = Color(0xFFE5E4E4),
                        disabledContainerColor = Color(0xFFE5E4E4)
                    ),
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Last Name",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Enter Last Name") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE5E4E4),
                        unfocusedContainerColor = Color(0xFFE5E4E4),
                        disabledContainerColor = Color(0xFFE5E4E4)
                    ),
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Email",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Enter Email") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE5E4E4),
                        unfocusedContainerColor = Color(0xFFE5E4E4),
                        disabledContainerColor = Color(0xFFE5E4E4)
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Box {
                        Column(
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text("Profile ", fontSize = 20.sp)
                            Text("Picture:", fontSize = 20.sp)
                        }
                    }

                    Box {
                        Column(
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text("Background", fontSize = 20.sp)
                            Text("Picture:", fontSize = 20.sp)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFFE5E4E4), shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = "Background Image",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("BROWSE", fontSize = 14.sp)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFFE5E4E4), shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = "Background Image",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("BROWSE", fontSize = 14.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(
                        onClick = {
                            if (email.isNotEmpty()) {
                                viewModel.updateProfile(email, firstName, lastName, email, null)
                                navController.navigate(Screen.ProfileScreen.route)
                            }
                        },
                        enabled = !state.isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Save", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { navController.navigate("profile") },
                        enabled = !state.isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF44336)
                        )
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                }
            }
        }
    }
}