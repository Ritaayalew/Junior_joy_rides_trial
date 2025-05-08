package com.example.trial_junior.feature_junior.presentation.screens


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trial_junior.feature_junior.presentation.viewModels.Invitation_Update.InvitationNewUpdateEvent
import com.example.trial_junior.feature_junior.presentation.viewModels.Invitation_Update.InvitationNewUpdateViewModel

import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.ui.graphics.Color




import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import java.util.Calendar


@Composable
fun Invitation() {
    val (currentForm, setCurrentForm) = rememberSaveable { mutableStateOf("invitation") }
    val defaultPadding = 16.dp

    Column(
        modifier = Modifier.fillMaxSize().padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Invitation",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = defaultPadding)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Invitation",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.clickable { setCurrentForm("invitation") }
            )
            Text(
                text = "Wishlist",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.clickable { setCurrentForm("wishlist") }
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(1f).height(8.dp), // Adjust height for better visibility
                onClick = { },
                colors = if (currentForm == "invitation") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D)) // Gold
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3)) // Light Grey
                }
            ) {

            }

            Button(
                modifier = Modifier.weight(1f).height(8.dp), // Same weight for equal width
                onClick = { },
                colors = if (currentForm == "wishlist") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D)) // Gold
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3)) // Light Grey
                }
            ) {

            }
        }

        Divider(modifier = Modifier.fillMaxWidth().height(2.dp))

        Spacer(modifier = Modifier.height(16.dp))

        when (currentForm) {
            "wishlist" -> WishList(viewModel = hiltViewModel()) // Passing the viewModel here
            else -> InviteEthiopis(viewModel = hiltViewModel()) // Also passing the viewModel here
        }


    }
}



@Composable
fun InviteEthiopis(viewModel: InvitationNewUpdateViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val spacing = 16.dp

    val calendar = remember { Calendar.getInstance() }

    // Date Picker Dialog
    val datePickerDialog = remember {
        DatePickerDialog (
            context,
            { _, year, month, day ->
                val selectedDate = "${year}-${month + 1}-${day}"
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredDate(selectedDate))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    // Time Picker Dialog
    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                val timeInMillis = hourOfDay * 3600000L + minute * 60000L
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredTime(timeInMillis))
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
    }

    // Event collector
    LaunchedEffect(true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is InvitationNewUpdateViewModel.UiEvent.ShowSnackbar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                InvitationNewUpdateViewModel.UiEvent.SaveInvitation -> {
                    Toast.makeText(context, "Invitation saved!", Toast.LENGTH_SHORT).show()
                }
                InvitationNewUpdateViewModel.UiEvent.Back -> {
                    // Handle back if needed
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Book a Mascot",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = spacing)
        )

        LoginTextField(
            value = state.invitation.childName,
            labelText = "Child's Name",
            onValueChange = {
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredChildName(it))
            },
            readOnly = true
        )
        Spacer(Modifier.height(spacing))
        LoginTextField(
            value = state.invitation.guardianEmail,
            labelText = "Guardian Email",
            onValueChange = {
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredGuardianEmail(it))
            },
            readOnly = true
        )
        Spacer(Modifier.height(spacing))
        LoginTextField(
            value = state.invitation.guardianPhone.takeIf { it > 0 }?.toString() ?: "",
            labelText = "Phone Number",
            onValueChange = { input ->
                val parsed = input.filter { it.isDigit() }.toLongOrNull() ?: 0L
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredGuardianPhone(parsed))
            },
            readOnly = false
        )


        Spacer(Modifier.height(spacing))
        LoginTextField(
            value = state.invitation.address,
            labelText = "Address",
            onValueChange = {
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredAddress(it))
            },
            readOnly = true
        )
        Spacer(Modifier.height(spacing))

        // 📅 Date Field with Dialog Trigger
        TextField(
            value = state.invitation.date,
            onValueChange = {}, // No-op
            label = { Text("Date") },
            modifier = Modifier
                .fillMaxWidth()

                .clickable { datePickerDialog.show()
                    },
            enabled = false,

            shape = RoundedCornerShape(percent = 30),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE7E7E7), // Light grey
                unfocusedContainerColor = Color(0xFFE7E7E7),
                disabledContainerColor = Color(0xFFE7E7E7),
                focusedIndicatorColor = Color.Transparent, // Removes outline when focused
                unfocusedIndicatorColor = Color.Transparent, // Removes outline when unfocused
                disabledIndicatorColor = Color.Transparent // Removes outline when disabled
            )

        )
        Spacer(Modifier.height(spacing))

// ⏰ Time Field with Dialog Trigger
        TextField(
            value = formatTime(state.invitation.time),
            onValueChange = {},
            label = { Text("Time") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { timePickerDialog.show() }

                , // Apply same radius for the shape
            enabled = false,
            shape = RoundedCornerShape(percent = 30),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE7E7E7), // Light grey
                unfocusedContainerColor = Color(0xFFE7E7E7),
                disabledContainerColor = Color(0xFFE7E7E7),
                focusedIndicatorColor = Color.Transparent, // Removes outline when focused
                unfocusedIndicatorColor = Color.Transparent, // Removes outline when unfocused
                disabledIndicatorColor = Color.Transparent // Removes outline when disabled
            )
        )


        Spacer(Modifier.height(spacing))

        LoginTextField(
            value = state.invitation.age.toString(),
            labelText = "Child's Age",
            onValueChange = {
                val parsed = it.toIntOrNull() ?: 0
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredAge(parsed))
            },
            readOnly = true
        )
        Spacer(Modifier.height(spacing))

        LoginTextField(
            value = state.invitation.specialRequests,
            labelText = "Special Requests",
            onValueChange = {
                viewModel.onEvent(InvitationNewUpdateEvent.EnteredSpecialRequests(it))
            },
            readOnly = true
        )
        Spacer(Modifier.height(spacing))

        Button(
            onClick = {
                viewModel.onEvent(InvitationNewUpdateEvent.SaveInvitation)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D))
        ) {
            Text("Submit Request")
        }
    }
}

// Helper function to format time from milliseconds to HH:mm
fun formatTime(millis: Long): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = millis
    }
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return String.format("%02d:%02d", hour, minute)
}


@Preview(showSystemUi = true)
@Composable
fun PrevInvitation() {
    Invitation()
}