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
import androidx.navigation.compose.rememberNavController
import com.example.buildconnectapp.model.Usuario
import com.example.buildconnectapp.navigation.AppScreens
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

@Composable
fun SignUpScreen(navController: NavController) {
    val database = FirebaseDatabase.getInstance()
    val userRef = database.getReference("usuario")
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var numberHouse by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var user by remember {
        mutableStateOf(
            Usuario(
                UUID.randomUUID().toString(),
                "",
                "",
                "",
                "",
                ""
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color(0xFF8BBB96)),
        verticalArrangement = Arrangement.Center

    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color(0xFFB4E1BE)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Registro de Usuario",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electronico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Numero de telefono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
            value = numberHouse,
            onValueChange = { numberHouse = it },
            label = { Text("Nomenclatura de vivienda") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
                val userMap = mapOf(
                    "id" to user.id,
                    "nombre" to username,
                    "correo_electronico" to email,
                    "telefono" to phoneNumber,
                    "contraseña" to password,
                    "numero_vivienda" to numberHouse
                )
                if(!username.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()){
                    userRef.child(user.id).setValue(userMap)
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006557),
                disabledContainerColor = Color(0xFF006557),
                contentColor = Color.White
            )
        ) {
            Text("Registrar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}