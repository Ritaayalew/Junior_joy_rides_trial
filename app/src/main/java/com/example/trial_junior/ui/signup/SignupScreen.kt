package com.example.firstjetpack.ui.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstjetpack.ui.components.HeaderText
import com.example.firstjetpack.ui.components.LoginTextField
import com.example.firstjetpack.ui.login.JetLoginTheme
import com.example.firstjetpack.ui.login.defaultPadding
import com.example.firstjetpack.ui.login.itemSpacing

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    onPolicyClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    val (firstName, setFirstName) = rememberSaveable { mutableStateOf("") }
    val (lastName, setLastName) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = rememberSaveable { mutableStateOf("") }
    val (agreed, onAgreeChange) = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val isPasswordSame = password == confirmPassword
    val isFieldsNotEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() &&
            password.isNotEmpty() && confirmPassword.isNotEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(!isPasswordSame) {
                Text("Passwords do not match", color = MaterialTheme.colorScheme.error)
            }
            HeaderText(
                text = "Signup",
                modifier = Modifier.padding(vertical = defaultPadding)
            )
            LoginTextField(
                value = firstName,
                onValueChange = setFirstName,
                labelText = "First Name",
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(itemSpacing))
            LoginTextField(
                value = lastName,
                onValueChange = setLastName,
                labelText = "Last Name",
                leadingIcon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(itemSpacing))
            LoginTextField(
                value = email,
                onValueChange = setEmail,
                labelText = "Email",
                leadingIcon = Icons.Default.Email,
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
            LoginTextField(
                value = confirmPassword,
                onValueChange = setConfirmPassword,
                labelText = "Confirm Password",
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(defaultPadding + 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(agreed, onAgreeChange)
                val privacyText = "Privacy"
                val policyText = "Policy"
                val annotatedString = buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append("I agree with ")
                    }
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        pushStringAnnotation(tag = privacyText, annotation = privacyText)
                        append(privacyText)
                    }
                    append(" and ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        pushStringAnnotation(tag = policyText, annotation = policyText)
                        append(policyText)
                    }
                }
                ClickableText(annotatedString) { offset ->
                    annotatedString.getStringAnnotations(offset, offset).forEach {
                        when (it.tag) {
                            privacyText -> {
                                Toast.makeText(context, "Privacy clicked", Toast.LENGTH_SHORT).show()
                                onPrivacyClick()
                            }
                            policyText -> {
                                Toast.makeText(context, "Policy clicked", Toast.LENGTH_SHORT).show()
                                onPolicyClick()
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(defaultPadding + 8.dp))
            Button(
                onClick = {
                    if (!agreed) {
                        Toast.makeText(context, "Please accept terms to Signup", Toast.LENGTH_SHORT).show()
                    } else if (!isPasswordSame) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                        onSignupClick()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isFieldsNotEmpty
            ) {
                Text("Signup")
            }
            Spacer(Modifier.height(defaultPadding + 8.dp))
            val signTx = "Sign in"
            val signInAnnotation = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("Already have an account? ")
                }
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(signTx, signTx)
                    append(signTx)
                }
            }
            ClickableText(
                signInAnnotation
            ) { offset ->
                signInAnnotation.getStringAnnotations(offset, offset).forEach {
                    if (it.tag == signTx) {
                        Toast.makeText(context, "Sign in clicked", Toast.LENGTH_SHORT).show()
                        onLoginClick() // Trigger navigation here
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun prevSignUp() {
    JetLoginTheme {
        SignUpScreen({}, {}, {}, {})
    }
}