package com.example.buildconnectapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.buildconnectapp.model.Usuario
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(navController: NavController, userId: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val database = FirebaseDatabase.getInstance()
    val userRef = database.getReference("usuario")
    val isNewUser = userId.isEmpty() || userId == "0"
    var user by remember {
        mutableStateOf(
            if (isNewUser) Usuario(
                UUID.randomUUID().toString(),
                "",
                "",
                "",
                "",
                ""
            ) else Usuario("", "", "", "", "", "")
        )
    }
    var isLoaded by remember { mutableStateOf(false) }

    if (!isNewUser && !isLoaded) {
        LaunchedEffect(Unit) {
            userRef.child(userId).get().addOnSuccessListener { snapshot ->
                user = snapshot.getValue(Usuario::class.java) ?: Usuario("", "", "", "", "", "")
                isLoaded = true
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(0xFF8BBB96),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0EAD6),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        if (isNewUser) "Agregar Usuario" else "Editar Usuario",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            tint = Color.Black,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val userMap = mapOf(
                    "id" to user.id,
                    "nombre" to user.nombre,
                    "correo_electronico" to user.correo_electronico,
                    "telefono" to user.telefono,
                    "contraseña" to user.contraseña,
                    "numero_vivienda" to user.numero_vivienda
                )
                if (user!= null && !user.nombre.isNullOrEmpty() && !user.correo_electronico.isNullOrEmpty() && !user.contraseña.isNullOrEmpty()) {
                    if (isNewUser) {
                        userRef.child(user.id).setValue(userMap)
                    } else {
                        userRef.child(user.id).updateChildren(userMap)
                    }
                }
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.Check, contentDescription = "Guardar Usuario")
            }
        },

        content = {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)) {
                Spacer(modifier = Modifier.height(100.dp))
                TextField(
                    value = user.nombre,
                    onValueChange = { user = user.copy(nombre = it) },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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
                    value = user.correo_electronico,
                    onValueChange = { user = user.copy(correo_electronico = it) },
                    label = { Text("Correo Electronico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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
                    value = user.telefono,
                    onValueChange = { user = user.copy(telefono = it) },
                    label = { Text("Telefono") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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
                    value = user.contraseña,
                    onValueChange = { user = user.copy(contraseña = it) },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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
                    value = user.numero_vivienda,
                    onValueChange = { user = user.copy(numero_vivienda = it) },
                    label = { Text("Numero de Vivienda") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UserFormPreview() {
    val navController = rememberNavController()
    UserFormScreen(navController, "1")
}