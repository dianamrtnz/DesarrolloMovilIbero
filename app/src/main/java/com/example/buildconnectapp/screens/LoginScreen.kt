package com.example.buildconnectapp.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buildconnectapp.helper.PreferencesHelper
import com.example.buildconnectapp.model.Usuario
import com.example.buildconnectapp.navigation.AppScreens
import com.example.buildconnectapp.ui.theme.BuildConnectAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun LoginScreen(navController : NavController) {
    val database = FirebaseDatabase.getInstance()
    val userRef = database.getReference("usuario")
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)

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
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
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
                    userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var loginSuccessful = false
                            for (userSnapshot in snapshot.children) {
                                val map = userSnapshot.getValue()
                                if (map != null && map != "") {
                                    val user = userSnapshot.getValue(Usuario::class.java)
                                    if (user != null && user.correo_electronico == email && user.contraseña == password) {
                                        username = user.nombre
                                        preferencesHelper.saveUserName(username)
                                        loginSuccessful = true
                                        isLoggedIn = true
                                        errorMessage = ""
                                        navController.navigate(route = AppScreens.MenuScreen.route)
                                        break
                                    }
                                }
                            }
                            if (!loginSuccessful) {
                                Toast.makeText(
                                    navController.context,
                                    "Usuario o contraseña incorrectos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(
                                navController.context,
                                "Error: ${error.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                },
                /*onClick = {
                    if (username == "user" && password == "123456") {
                        isLoggedIn = true
                        errorMessage = ""
                        navController.navigate(route = AppScreens.MenuScreen.route)
                    } else {
                        errorMessage = "Usuario o contraseña incorrectos"
                    }
                },*/
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


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}