package com.example.bookinghotelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookinghotelapp.ui.BookingSummaryScreen
import com.example.bookinghotelapp.ui.RoomDetailScreen
import com.example.bookinghotelapp.ui.RoomListScreen
import com.example.bookinghotelapp.viewmodel.RoomViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel = RoomViewModel()

    setContent {
      val navController = rememberNavController()

      NavHost(
        navController = navController,
        startDestination =  "roomList",
        modifier = Modifier
          .padding(WindowInsets.systemBars.asPaddingValues())
      ){
        composable("roomList"){
          RoomListScreen(viewModel, navController)
        }

        composable("roomDetail"){
          RoomDetailScreen(viewModel, navController)
        }

        composable("bookingSummary"){
          BookingSummaryScreen(viewModel, navController)
        }
      }
    }
  }
}
