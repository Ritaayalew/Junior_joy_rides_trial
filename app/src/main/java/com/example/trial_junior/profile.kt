package com.example.trial_junior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trial_junior.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "profile") {
                composable("profile") { ProfileScreen(navController) }
                composable("edit_profile") { EditProfileScreen(navController) }
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
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
                text = "Full Name",
                fontSize = 30.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "name@gmail.com",
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.navigate("edit_profile") },
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

        // Cards
        items(2) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .shadow(elevation = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F8F8))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (index == 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Birthday Celebration", fontSize = 18.sp, color = Color.Black)
                            Button(onClick = { /* Handle button click */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF9A927)
                                )) {
                                Text("PENDING")
                            }
                        }
                        Text(text = "Date 2025-03-27", fontSize = 12.sp, color = Color.Black)
                        Text(text = "Birthday celebration with ethiopis", fontSize = 12.sp, color = Color.Black)
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Interview", fontSize = 18.sp, color = Color.Black)
                            Button(onClick = { /* Handle button click */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4B951F)
                                )) {
                                Text("APPROVED")
                            }
                        }
                        Text(text = "Date 2025-03-27", fontSize = 12.sp, color = Color.Black)
                        Text(text = "Story telling for talent showcasing", fontSize = 12.sp, color = Color.Black)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { /* Handle button click */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF9F8F8)
                            )) {
                            Text("Edit",
                                color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { /* Handle button click */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF9F8F8)
                            )) {
                            Text("Cancle",
                                color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}



@Composable

fun EditProfileScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
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
                text = "Full Name",
                fontSize = 30.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "name@gmail.com",
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
                shape = RoundedCornerShape(8.dp), // Optional: customize card shape if needed
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F8F8)) // Set card color
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
                    shape = RoundedCornerShape(10.dp), // Set rounded corners
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
                    shape = RoundedCornerShape(10.dp), // Set rounded corners
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        ))
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
                    shape = RoundedCornerShape(10.dp), // Set rounded corners
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        ))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)


                ) {
                    Box(

                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top
                        ) {

                            Text("Profile ", fontSize = 20.sp)
                            Text("Picture:",fontSize = 20.sp)
                        }
                    }

                    Box(){
                        Column(
                            verticalArrangement = Arrangement.Top
                        ){
                            Text("Background", fontSize = 20.sp)
                            Text("Picture:",fontSize = 20.sp)
                        }

                    }


                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
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
                                .padding(8.dp), // Add padding for spacing
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = "Background Image",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp)) // Add spacer for spacing
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
                                .padding(8.dp), // Add padding for spacing
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = "Background Image",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp)) // Add spacer for spacing
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
                        onClick = { /* Handle Save Action */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50) // Green Background for Save Button
                        )
                    ) {
                        Text("Save", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Add some spacing
                    Button(
                        onClick = { navController.navigate("profile")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF44336) // Red Background for Cancel Button
                        )
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                }
            }
        }
    }
}


@Preview(name = "Portrait Preview", showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}