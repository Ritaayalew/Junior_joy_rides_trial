package com.example.firstjetpack.ui.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
//import com.example.firstjetpack.R
import com.example.firstjetpack.ui.components.HeaderText
import com.example.firstjetpack.ui.components.LoginTextField
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.platform.LocalContext

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(onLoginClick:()->Unit,onSignupClick: () -> Unit) {
    val (userName, setUserName) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (checked, onCheckedChange) = rememberSaveable { mutableStateOf(false) }
    val isFieldEmpty=userName.isNotEmpty()&& password.isNotEmpty()

    val context= LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding) // Ensures the layout occupies full screen space
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(
                text = "Login",
                modifier = Modifier.padding(vertical = defaultPadding)
            )
            LoginTextField(
                value = userName,
                onValueChange = setUserName,
                labelText = "Username",
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(itemSpacing))
            LoginTextField(
                value = password,
                onValueChange = setPassword,
                labelText = "Password",
                leadingIcon = Icons.Default.Lock,
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(itemSpacing))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                    Text("Remember me")
                }
                TextButton(onClick = {}) {
                    Text("Forgot Password")
                }
            }
            Spacer(Modifier.height(itemSpacing))
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth(),
                enabled = isFieldEmpty
            ) {
                Text("Login")
            }
        }

        // Add the icons at the bottom
        AlternativeLoginOptions(
            onIconClick = {index ->
                when(index){
                    0 ->{
                        Toast.makeText(context, "Instagram Login Click", Toast.LENGTH_SHORT).show()
                    }
                    1 ->{
                        Toast.makeText(context, "github Login Click", Toast.LENGTH_SHORT).show()
                    }
                    2 ->{
                        Toast.makeText(context, "telegram Login Click", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onSignupClick = onSignupClick,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Aligns at the bottom center
                .padding(bottom = defaultPadding) // Adds some spacing
        )
    }
}


@Composable
fun AlternativeLoginOptions(
    onIconClick:(index:Int)->Unit,
    onSignupClick:()->Unit,
    modifier:Modifier= Modifier) {
//
//    val iconList= listOf(
//        R.drawable.ic_launcher_background,
//        R.drawable.ic_launcher_background ,
//        R.drawable.ic_launcher_foreground,
//    )
    Column(modifier=modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ){
        Text("Or signin with")
//        Row(horizontalArrangement =Arrangement.SpaceEvenly,
//           ) {
//            iconList.forEachIndexed { index, iconResId ->
//                Icon(
//                    painter = painterResource(iconResId),
//                    contentDescription = "Alternative Login",
//                    modifier=Modifier
//                        .size(32.dp)
//                        .clickable {
//                            onIconClick(index)
//                        }
//                        .clip(CircleShape))
//
//                Spacer(Modifier.width(itemSpacing))
//        }

            }
        Row(horizontalArrangement =Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")
            TextButton(onClick = onSignupClick
            ) {
                Text("Sign Up")
            }
             }
    }


@Composable
fun JetLoginTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        // Customize your theme (e.g., colors, typography, shapes) here if needed
        content = content
    )
}

@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {
    JetLoginTheme {
        LoginScreen({},{})
    }
}

