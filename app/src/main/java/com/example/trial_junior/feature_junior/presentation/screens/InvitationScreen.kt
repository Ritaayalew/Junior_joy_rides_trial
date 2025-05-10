package com.example.trial_junior.feature_junior.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trial_junior.feature_junior.presentation.viewModels.Invitation_Update.InvitationNewUpdateEvent
import com.example.trial_junior.feature_junior.presentation.viewModels.Invitation_Update.InvitationNewUpdateViewModel
import com.example.trial_junior.feature_junior.presentation.components.HintInputField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.trial_junior.feature_junior.presentation.util.Screen
import java.util.Calendar
import java.util.Locale

@Composable
fun InvitationScreen(navController: NavHostController, viewModel: InvitationNewUpdateViewModel = hiltViewModel()) {
    val (currentForm, setCurrentForm) = rememberSaveable { mutableStateOf("invitation") }
    val defaultPadding = 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Birthday",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 5.dp),
        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(0.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Invite Etopis",
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
                modifier = Modifier.clickable { navController.navigate(Screen.WishListScreen.route) }
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(1f).height(8.dp),
                onClick = { },
                colors = if (currentForm == "invitation") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D))
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3))
                }
            ) {}
            Button(
                modifier = Modifier.weight(1f).height(8.dp),
                onClick = { },
                colors = if (currentForm == "wishlist") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D))
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3))
                }
            ) {}
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp))

        Invitation(viewModel = viewModel)
    }
}

@Composable
fun Invitation(viewModel: InvitationNewUpdateViewModel) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val spacing = 16.dp
    val snackbarHostState = remember { SnackbarHostState() }

    val calendar = remember { Calendar.getInstance() }

    // Date Picker Dialog
    val datePickerDialog = remember {
        DatePickerDialog(
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
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is InvitationNewUpdateViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar("Submission failed: ${event.message}")
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                InvitationNewUpdateViewModel.UiEvent.SaveInvitation -> {
                    snackbarHostState.showSnackbar("Successfully submitted!")
                    Toast.makeText(context, "Invitation saved!", Toast.LENGTH_SHORT).show()
                }
                InvitationNewUpdateViewModel.UiEvent.Back -> {
                    // Handle back if needed
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Book a Mascot",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = spacing)
                )
            }

            item {
                HintInputField(
                    value = state.invitation.childName,
                    hint = "Child's Name",
                    onValueChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.EnteredChildName(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.ChangedChildNameFocus(it))
                    }
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                HintInputField(
                    value = state.invitation.guardianEmail,
                    hint = "Guardian Email",
                    keyboardType = KeyboardType.Email,
                    onValueChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.EnteredGuardianEmail(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.ChangedGuardianEmailFocus(it))
                    }
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                HintInputField(
                    value = state.invitation.guardianPhone.takeIf { it > 0 }?.toString() ?: "",
                    hint = "Phone Number",
                    keyboardType = KeyboardType.Phone,
                    onValueChange = { input ->
                        val parsed = input.filter { it.isDigit() }.toLongOrNull() ?: 0L
                        viewModel.onEvent(InvitationNewUpdateEvent.EnteredGuardianPhone(parsed))
                    },
                    onFocusChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.ChangedGuardianPhoneFocus(it))
                    }
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                HintInputField(
                    value = state.invitation.address,
                    hint = "Address",
                    onValueChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.EnteredAddress(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.ChangedAddressFocus(it))
                    }
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                // 📅 Date Field with Dialog Trigger
                TextField(
                    value = state.invitation.date,
                    onValueChange = {},
                    placeholder = { Text("Date", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { datePickerDialog.show() },
                    enabled = false,
                    shape = RoundedCornerShape(percent = 30),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE7E7E7),
                        unfocusedContainerColor = Color(0xFFE7E7E7),
                        disabledContainerColor = Color(0xFFE7E7E7),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                // ⏰ Time Field with Dialog Trigger
                TextField(
                    value = formatTime(state.invitation.time),
                    onValueChange = {},
                    placeholder = { Text("Time", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { timePickerDialog.show() },
                    enabled = false,
                    shape = RoundedCornerShape(percent = 30),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE7E7E7),
                        unfocusedContainerColor = Color(0xFFE7E7E7),
                        disabledContainerColor = Color(0xFFE7E7E7),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                HintInputField(
                    value = state.invitation.age.takeIf { it > 0 }?.toString() ?: "",
                    hint = "Child's Age",
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        val parsed = it.toIntOrNull() ?: 0
                        viewModel.onEvent(InvitationNewUpdateEvent.EnteredAge(parsed))
                    },
                    onFocusChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.ChangedAgeFocus(it))
                    }
                )
                Spacer(Modifier.height(spacing))
            }

            item {
                HintInputField(
                    value = state.invitation.specialRequests,
                    hint = "Special Requests",
                    onValueChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.EnteredSpecialRequests(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(InvitationNewUpdateEvent.ChangedSpecialRequestsFocus(it))
                    }
                )
                Spacer(Modifier.height(spacing))
            }

            item {
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
    }
}

// Helper function to format time from milliseconds to HH:mm
fun formatTime(millis: Long): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = millis
    }
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
}

