package com.example.wordpressclient.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.wordpressclient.R



@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    onGuestClick: () -> Unit,
    registeredProvider: () -> List<Pair<String, String>>
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf<String?>(null) }
    var attempted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium,modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                if (attempted) errorText = null
            },
            label = { Text("Enter your mobile number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                if (attempted) errorText = null
            },
            label = { Text("Enter your password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (errorText != null) {
            Text(errorText!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                attempted = true
                when {
                    phone.isBlank() -> errorText = "Please enter phone number"
                    password.isBlank() -> errorText = "Please enter password"
                    else -> {
                        onLoginClick(phone.trim(), password)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don’t have an account? ")
            Text(
                "Sign Up",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }

        OutlinedButton(
            onClick = { /* TODO: Google Sign In */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google logo",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Continue with Google")
        }

        OutlinedButton(
            onClick = { /* TODO: Apple Sign In */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_apple),
                contentDescription = "Apple logo",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Continue with Apple")
        }

        OutlinedButton(
            onClick = { /* TODO: Facebook Sign In */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "Facebook logo",
                tint = Color.Unspecified, // giữ màu xanh Facebook
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Continue with Facebook")
        }


        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "Continue as Guest",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onGuestClick() }
        )
    }
}
