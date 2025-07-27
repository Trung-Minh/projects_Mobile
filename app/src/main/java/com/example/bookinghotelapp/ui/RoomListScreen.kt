package com.example.bookinghotelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bookinghotelapp.data.provideRoomList
import com.example.bookinghotelapp.viewmodel.RoomViewModel

@Composable
fun RoomListScreen(
  viewModel: RoomViewModel = viewModel(),
  navController: NavController
){
  val context = LocalContext.current

  LaunchedEffect(Unit) {
    if (viewModel.rooms.isEmpty()) {
      viewModel.updateRooms(provideRoomList(context))
    }
  }

  Column (
    modifier = Modifier.padding(16.dp)
  ) {
    Text(
      "Danh sách phòng",
      style = MaterialTheme.typography.headlineMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
      items(viewModel.rooms) { room ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(bottom = 8.dp)
            .clickable {
              viewModel.selectRoom(room)
              navController.navigate("roomDetail")
            }
        ) {
          Column {
            Image(
              painter = painterResource(id = room.imageList.first()),
              contentDescription =  null,
              modifier = Modifier
                .padding(top =  8.dp)
                .fillMaxWidth()
                .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column (
              modifier = Modifier.padding(start = 16.dp)
            ) {
              Text(
                room.name,
                style = MaterialTheme.typography.titleLarge
              )
              Text("Giá: ${room.price} VNĐ/đêm")
              Text("Còn ${room.availableRooms} phòng")
              Spacer(modifier = Modifier.height(16.dp))
            }
          }
        }
      }
    }
  }
}