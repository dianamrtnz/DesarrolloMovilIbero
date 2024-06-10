package com.example.buildconnectapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
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
import androidx.navigation.compose.rememberNavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController : NavController){
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
                    IconButton(onClick = { navController.popBackStack()  }) {
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
            CalendarBodyContent()
        }
    )
}

@Composable
fun CalendarBodyContent(){
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
                text = "Calendario",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        CustomDatePicker()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker() {
    val state = rememberDatePickerState()
    DatePicker(
        state = state,
        Modifier.padding(horizontal = 5.dp).background(Color.White),
        colors = DatePickerDefaults.colors(
            containerColor = Color.Black,
            dayContentColor = Color.Black,
            subheadContentColor = Color.Black,
            weekdayContentColor = Color.Black,
            headlineContentColor = Color.Black,
        )
    )
}


@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    val navController = rememberNavController()
    CalendarScreen(navController)
}
