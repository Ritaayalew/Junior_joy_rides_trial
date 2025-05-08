package com.example.trial_junior.feature_junior.presentation.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.trial_junior.feature_junior.presentation.viewModels.BasicInterview_Update.BasicInterviewNewUpdateEvent
import com.example.trial_junior.feature_junior.presentation.viewModels.BasicInterview_Update.BasicInterviewNewUpdateViewModel



@Composable
fun Interview(viewModel: BasicInterviewNewUpdateViewModel = hiltViewModel()) {
    val (currentForm, setCurrentForm) = rememberSaveable { mutableStateOf("BasicInterview") }
    val defaultPadding = 16.dp

    Column(
        modifier = Modifier.fillMaxSize().padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Interview",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = defaultPadding)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Basic Interview",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.clickable { setCurrentForm("BasicInterview") }
            )
            Text(
                text = "Talent Show",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.clickable { setCurrentForm("TalentShow") }
            )
        }

        // Toggle Buttons
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(1f).height(8.dp),
                onClick = { setCurrentForm("BasicInterview") },
                colors = if (currentForm == "BasicInterview") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D)) // Gold
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3)) // Light Grey
                }
            ) {

            }

            Button(
                modifier = Modifier.weight(1f).height(8.dp),
                onClick = { setCurrentForm("TalentShow") },
                colors = if (currentForm == "TalentShow") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D)) // Gold
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3)) // Light Grey
                }
            ) {

            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Show the selected form
        when (currentForm) {
            "TalentShow" -> TalentShowForm()
            else -> BasicInterview(viewModel) // ✅ Pass ViewModel correctly
        }
    }
}

@Composable
fun BasicInterview(viewModel: BasicInterviewNewUpdateViewModel) {
    val state = viewModel.state.value
    val itemSpacing = 16.dp

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            text = "Read fairy tale with Ethiopis"
        )

        LoginTextField(
            value = state.basicInterview.childName,
            labelText = "Child's Name",
            onValueChange = { viewModel.onEvent(BasicInterviewNewUpdateEvent.EnteredChildName(it)) },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = state.basicInterview.age.takeIf { it > 0 }?.toString() ?: "",
            labelText = "Age",
            onValueChange = {
                it.toIntOrNull()?.let { age -> viewModel.onEvent(BasicInterviewNewUpdateEvent.EnteredAge(age)) }
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = state.basicInterview.guardianName,
            labelText = "Guardian Name",
            onValueChange = { viewModel.onEvent(BasicInterviewNewUpdateEvent.EnteredGuardianName(it)) },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = state.basicInterview.guardianEmail,
            labelText = "Email",
            onValueChange = { viewModel.onEvent(BasicInterviewNewUpdateEvent.EnteredGuardianEmail(it)) },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = if (state.basicInterview.guardianPhone > 0) state.basicInterview.guardianPhone.toString() else "",
            labelText = "Phone Number",
            onValueChange = {
                it.toLongOrNull()?.let { phone -> viewModel.onEvent(BasicInterviewNewUpdateEvent.EnteredGuardianPhone(phone)) }
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = state.basicInterview.specialRequests,
            labelText = "Special Requirements",
            onValueChange = { viewModel.onEvent(BasicInterviewNewUpdateEvent.EnteredSpecialRequests(it)) },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        val isFormValid = state.basicInterview.run {
            childName.isNotBlank() && guardianName.isNotBlank() &&
                    guardianPhone > 0 && age > 0 && guardianEmail.isNotBlank()
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onEvent(BasicInterviewNewUpdateEvent.SaveBasicInterview) },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D))
        ) {
            Text("Submit Request")
        }
    }
}


// 🖥 Preview Function
@Preview(showSystemUi = true)
@Composable
fun PrevInterview() {
    Interview()
}