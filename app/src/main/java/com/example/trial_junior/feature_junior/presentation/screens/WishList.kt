package com.example.trial_junior.feature_junior.presentation.screens

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trial_junior.feature_junior.presentation.viewModels.WishList_Update.WishListNewUpdateEvent
import com.example.trial_junior.feature_junior.presentation.viewModels.WishList_Update.WishListNewUpdateViewModel
import java.util.Calendar

@Composable
fun WishList(viewModel: WishListNewUpdateViewModel) {
    val context = LocalContext.current
    val state = viewModel.state.value

    val (name, setName) = rememberSaveable { mutableStateOf(state.wishlist.childName) }
    val (celebrationDate, setCelebrationDate) = rememberSaveable { mutableStateOf(state.wishlist.date) }
    val (email, setEmail) = rememberSaveable { mutableStateOf(state.wishlist.guardianEmail) }
    val (age, setAge) = rememberSaveable { mutableStateOf(state.wishlist.age) }
    val (specialRequirement, setSpecialRequirement) = rememberSaveable { mutableStateOf(state.wishlist.specialRequests) }
    val (imageUrl, setImageUrl) = rememberSaveable { mutableStateOf("") }
    val itemSpacing = 16.dp

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val uri = result.data?.data
        uri?.let {
            val uriString = it.toString()
            setImageUrl(uriString)
            viewModel.onEvent(WishListNewUpdateEvent.EnteredImageUrl(uriString))
        }
    }

    val openImagePicker = {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        launcher.launch(intent)
    }

    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        android.app.DatePickerDialog(
            context,
            { _, year, month, day ->
                val selectedDate = "${year}-${month + 1}-${day}"
                viewModel.onEvent(WishListNewUpdateEvent.EnteredDate(selectedDate))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            "Join the Birthday Wishlist",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(itemSpacing))

        Text("Upload your child’s photo to be featured on our TV show this week!")

        Spacer(Modifier.height(itemSpacing))

        Text(
            text = if (imageUrl.isEmpty()) "No image selected" else imageUrl,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = openImagePicker,
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(100.dp)
                .padding(start = 56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Select Image",
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = name,
            labelText = "Child's Name",
            onValueChange = {
                setName(it)
                viewModel.onEvent(WishListNewUpdateEvent.EnteredChildName(it))
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = email,
            labelText = "Guardian Email",
            onValueChange = {
                setEmail(it)
                viewModel.onEvent(WishListNewUpdateEvent.EnteredGuardianEmail(it))
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = age.toString(),
            labelText = "Age",
            onValueChange = {
                val parsed = it.toIntOrNull() ?: 0
                setAge(parsed)
                viewModel.onEvent(WishListNewUpdateEvent.EnteredAge(parsed))
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = celebrationDate,
            labelText = "Date of Celebration",
            onValueChange = {
                setCelebrationDate(it)
                viewModel.onEvent(WishListNewUpdateEvent.EnteredDate(it))
            },
            modifier = Modifier.clickable {
                datePickerDialog.show()
            },
            readOnly = false
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = specialRequirement,
            labelText = "Special Requirements",
            onValueChange = {
                setSpecialRequirement(it)
                viewModel.onEvent(WishListNewUpdateEvent.EnteredSpecialRequests(it))
            },
            readOnly = false
        )

        Spacer(Modifier.height(itemSpacing))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.onEvent(WishListNewUpdateEvent.SaveWishList)
                Toast.makeText(context, "Submitted!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC5AE3D))
        ) {
            Text("Submit Request")
        }
    }
}
