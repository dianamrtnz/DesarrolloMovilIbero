package com.example.buildconnectapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buildconnectapp.model.Usuario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val database = FirebaseDatabase.getInstance()
    val userRef = database.getReference("usuario")
    var users by remember { mutableStateOf(listOf<Usuario>()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newUsers = mutableListOf<Usuario>()
                for (userSnapshot in snapshot.children) {
                    val map = userSnapshot.getValue()
                    if (map != null && map != "") {
                        val user = userSnapshot.getValue(Usuario::class.java)
                        if (user != null) {
                            newUsers.add(user)
                        }
                    }
                }
                users = newUsers
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                errorMessage = error.message
            }
        })
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
                        "Listado de Usuarios",
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
            FloatingActionButton(onClick = { navController.navigate("user_form_screen/0") }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Usuario")
            }
        },
        content = {
            Spacer(modifier = Modifier.height(80.dp))
            if (errorMessage != null) {
                Snackbar(
                    action = {
                        Button(onClick = { errorMessage = null }) {
                            Text("Quitar")
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = errorMessage ?: "")
                }
            }
            LazyColumn(modifier = Modifier.padding(vertical = 100.dp, horizontal = 16.dp)) {
                items(users, key = { it.id }) { user ->
                    UserItem(user = user, onEdit = {
                        navController.navigate("user_form_screen/${user.id}")
                    }, onDelete = {
                        userRef.child(user.id).removeValue()
                    })
                }
            }
        }
    )
}

@Composable
fun UserItem(user: Usuario, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onEdit() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${user.nombre}")
            Text(text = "Correo Electronico: ${user.correo_electronico}")
            Text(text = "Telefono: ${user.telefono}")
            Text(text = "Numero de Vivienda: ${user.numero_vivienda}")
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    val navController = rememberNavController()
    UserListScreen(navController)
}