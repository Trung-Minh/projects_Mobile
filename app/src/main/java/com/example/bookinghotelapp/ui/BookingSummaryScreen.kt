package com.example.bookinghotelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bookinghotelapp.viewmodel.RoomViewModel

@Composable
fun BookingSummaryScreen(
  viewModel: RoomViewModel = viewModel(),
  navController: NavController
){
  val room = viewModel.selectedRoom?: return

  val quantity = viewModel.quantity
  val totalPrice = quantity * room.price

  Column (
    modifier = Modifier
      .padding(16.dp)
      .fillMaxSize()
  ) {
    Text(
      "Tóm tắt đặt phòng",
      style = MaterialTheme.typography.headlineMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    Image(
      painter = painterResource(id = room.imageList.first()),
      contentDescription = null,
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      "Loại phòng: ${room.name}",
      style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      "Mô tả: ${room.description}",
      style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(2.dp))

    Text(
      "Giá mỗi đêm: ${room.price} VNĐ",
      style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(2.dp))

    Text(
      "Số lượng phòng: $quantity",
      style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(2.dp))

    Text(
      "Tổng cộng: $totalPrice VNĐ",
      style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    )


    Spacer(modifier = Modifier.height(24.dp))

    Text(
      "Vui lòng xác nhận đặt phòng và thanh toán trong vòng 3 giờ",
      color = MaterialTheme.colorScheme.error
    )

    Spacer(modifier = Modifier.height(24.dp))

    Button(
      onClick = {
        navController.popBackStack("roomList", inclusive = false)
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("Quay lại danh sách")
    }
  }
}