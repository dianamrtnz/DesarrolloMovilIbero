package com.example.buildconnectapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buildconnectapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(navController : NavController){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0EAD6),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "",
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
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
            ForumBodyContent()
        }
    )
}

@Composable
fun ForumBodyContent() {
    data class Topic(val name: String)
    val sampleData = listOf(
        Topic("Tema 1"),
        Topic("Tema 2"),
        Topic("Tema 3"),
        Topic("Tema 4"),
        // ...
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(0.dp).background(Color(0xFF8BBB96)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .background(Color(0xFFB4E1BE)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Foro Social",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
            },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006557),
                disabledContainerColor = Color(0xFF006557),
                contentColor = Color.White
            )
        ) {
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text("Agregar Tema de discusión")
                Spacer(modifier = Modifier.width(0.dp))
                Icon(Icons.Outlined.Add, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Temas existentes",
            modifier = Modifier.padding(horizontal = 0.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        for (item in sampleData) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 35.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(modifier = Modifier.padding(start = 16.dp)) {
                        Text(
                            text = item.name,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.width(150.dp))
                        Icon(Icons.Outlined.Edit, contentDescription = "Editar")
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ForumPreview() {
    ForumScreen()
}
*/