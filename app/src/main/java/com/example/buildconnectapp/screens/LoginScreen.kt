package com.example.buildconnectapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buildconnectapp.navigation.AppScreens
import com.example.buildconnectapp.ui.theme.BuildConnectAppTheme

@Composable
fun LoginScreen(navController : NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    if (isLoggedIn) {
        Text(text = "Bienvenido, $username!", style = MaterialTheme.typography.titleMedium)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFF8BBB96)),
            verticalArrangement = Arrangement.Center

        ) {
            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color(0xFFB4E1BE)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Inicio de Sesion",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFB4E1BE),
                    focusedContainerColor = Color(0xFFB4E1BE),
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color(0xFFD0EAD6)

                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFB4E1BE),
                    focusedContainerColor = Color(0xFFB4E1BE),
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color(0xFFD0EAD6)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (username == "user" && password == "123456") {
                        isLoggedIn = true
                        errorMessage = ""
                        navController.navigate(route = AppScreens.MenuScreen.route)
                    } else {
                        errorMessage = "Usuario o contraseña incorrectos"
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF006557),
                    disabledContainerColor = Color(0xFF006557),
                    contentColor = Color.White
                )
            ) {
                Text("Iniciar sesión")
            }
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.SignUpScreen.route)
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF006557),
                    disabledContainerColor = Color(0xFF006557),
                    contentColor = Color.White
                )
            ) {
                Text("Registrarse")
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color(0xFFB00020))
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen()
}
 */