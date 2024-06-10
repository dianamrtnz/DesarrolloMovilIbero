package com.example.buildconnectapp.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buildconnectapp.helper.PreferencesHelper
import com.example.buildconnectapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController : NavController) {
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
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
                        "Menu",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        preferencesHelper.clearUserName()
                        navController.popBackStack()
                    }) {
                        Icon(
                            tint = Color.Black,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            tint = Color.Black,
                            imageVector = Icons.Filled.Menu,
                            contentDescription = ""
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
            MenuBodyContent(navController)
        }
    )
}

@Composable
fun MenuBodyContent(navController : NavController){
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    val userName = preferencesHelper.getUserName()

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
                text = "Bienvenido Usuario : $userName",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                navController.navigate(route = AppScreens.AdvertisementsScreen.route)
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
                Text("Anuncios importantes")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Outlined.Notifications, contentDescription = "Anuncios importantes")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                navController.navigate(route = AppScreens.CalendarScreen.route)
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
                Text("Calendario")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Outlined.DateRange, contentDescription = "Calendario")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                navController.navigate(route = AppScreens.ForumScreen.route)
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
                Text("Foro Social")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Outlined.MailOutline, contentDescription = "Foro Social")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                navController.navigate(route = AppScreens.UserListScreen.route)
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
                Text("Gestion de Usuarios")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Outlined.AccountCircle, contentDescription = "Gestion de Usuarios")
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    val navController = rememberNavController()
    MenuScreen(navController)
}
