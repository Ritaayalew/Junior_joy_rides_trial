package com.example.trial_junior.feature_junior.presentation.screens


import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trial_junior.feature_junior.presentation.viewModels.SpecialInterview_Update.SpecialInterviewNewUpdateEvent
import com.example.trial_junior.feature_junior.presentation.viewModels.SpecialInterview_Update.SpecialInterviewNewUpdateViewModel


@Composable
fun SpecialInterviewScreen(viewModel: SpecialInterviewNewUpdateViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val (name, setName) = rememberSaveable { mutableStateOf("") }
    val (guardianName, setGuardianName) = rememberSaveable { mutableStateOf("") }
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (age, setAge) = rememberSaveable { mutableStateOf("") }
    val (phone, setPhone) = rememberSaveable { mutableStateOf("") }
    val (specialRequirement, setSpecialRequirement) = rememberSaveable { mutableStateOf("") }
    val (videoUrl, setVideoUrl) = rememberSaveable { mutableStateOf("") }

    val itemSpacing = 16.dp

    // File picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val uri = result.data?.data
        uri?.let {
            setVideoUrl(it.toString())
        }
    }

    val openDrivePicker = {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "video/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        launcher.launch(intent)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Enhance your child’s talent",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(itemSpacing))

        Text(
            text = if (videoUrl.isEmpty()) "No video selected" else videoUrl,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = openDrivePicker,
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(100.dp)
                .padding(start = 56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray // Set background color
            ),
            shape = RoundedCornerShape(15.dp) // Minimal border radius
        ) {
            Icon(
                imageVector = Icons.Outlined.Videocam,
                contentDescription = "Select Image",
                modifier = Modifier.size(32.dp), // Adjust size here
                tint = Color.Black               // Set the icon color to black
            )

        }
            Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = name,
            labelText = "Child's Name",
            onValueChange = setName,
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = age,
            labelText = "Age",
            onValueChange = { input ->
                if (input.isEmpty() || input.all { it.isDigit() }) setAge(input)
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = guardianName,
            labelText = "Guardian Name",
            onValueChange = setGuardianName,
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = email,
            labelText = "Email",
            onValueChange = setEmail,
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = phone,
            labelText = "Phone Number",
            onValueChange = setPhone,
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = specialRequirement,
            labelText = "Special Requirements",
            onValueChange = setSpecialRequirement,
            readOnly = false
        )

        Spacer(Modifier.height(itemSpacing))

        // Observe ViewModel Event Flow for Success/Error Messages
        val eventFlow = viewModel.eventFlow.collectAsState(initial = null)

        LaunchedEffect(eventFlow.value) {
            eventFlow.value?.let { event ->
                when (event) {
                    is SpecialInterviewNewUpdateViewModel.UiEvent.SaveSpecialInterview -> {
                        Toast.makeText(context, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
                    }
                    is SpecialInterviewNewUpdateViewModel.UiEvent.ShowSnackbar -> {
                        Toast.makeText(context, "Submission failed: ${event.message}", Toast.LENGTH_SHORT).show()
                    }
                    is SpecialInterviewNewUpdateViewModel.UiEvent.Back -> {
                        // Handle navigation or ignore if unnecessary
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val validatedAge = age.toIntOrNull()?.takeIf { it > 0 } ?: 0
                val validatedPhone = phone.toLongOrNull()?.takeIf { it > 0 } ?: 0L

                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredChildName(name))
                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredGuardianName(guardianName))
                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredGuardianPhone(validatedPhone))
                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredAge(validatedAge))
                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredGuardianEmail(email))
                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredSpecialRequests(specialRequirement))
                viewModel.onEvent(SpecialInterviewNewUpdateEvent.EnteredVideoUrl(videoUrl))

                viewModel.onEvent(SpecialInterviewNewUpdateEvent.SaveSpecialInterview)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D))
        ) {
            Text("Submit Request")
        }
    }
}