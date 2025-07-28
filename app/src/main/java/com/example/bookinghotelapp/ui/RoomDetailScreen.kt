package com.example.bookinghotelapp.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bookinghotelapp.viewmodel.RoomViewModel
import kotlinx.coroutines.launch

@Composable
fun RoomDetailScreen(
  viewModel: RoomViewModel =  viewModel(),
  navController: NavController
){
  val room = viewModel.selectedRoom?: return

  Column (
    modifier = Modifier
      .padding(16.dp)
      .fillMaxSize()
  ) {
    Text(
      "Chi tiết phòng",
      style = MaterialTheme.typography.headlineMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    RoomImageCarousel(room.imageList)

    Text(
      room.name,
      style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      "Mô tả: ${room.description}",
      style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(2.dp))

    Text(
      "Giá: ${room.price} VNĐ/đêm",
      style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(2.dp))

    Text(
      "Còn lại: ${room.availableRooms} phòng",
      style = MaterialTheme.typography.bodyLarge
    )

    Spacer(modifier = Modifier.height(16.dp))

    var quantityInput by remember { mutableStateOf(viewModel.quantity.toString()) }
    var showError by remember { mutableStateOf(false) }

    val available = viewModel.selectedRoom?.availableRooms ?: 1

    OutlinedTextField(
      value = quantityInput,
      onValueChange = {
        quantityInput = it
        showError = false // reset lỗi khi gõ lại
      },
      label = { Text("Số lượng phòng muốn đặt (tối đa $available)") },
      isError = showError,
      modifier = Modifier.fillMaxWidth()
    )

    if (showError) {
      Text(
        text = "⚠️ Số lượng phòng không hợp lệ",
        color = Color.Red,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 4.dp)
      )
    }

    Spacer(modifier = Modifier.height(24.dp))

    Row (
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Button(
        onClick =  {
          navController.popBackStack()
        }
      ){
        Text("Hủy")
      }

      Button(
        onClick = {
          val q = quantityInput.toIntOrNull()
          if (q != null && q in 1..available) {
            viewModel.updateQuantity(q)
            showError = false
            navController.navigate("bookingSummary")
          } else {
            showError = true
          }
        },
      ) {
        Text("Đặt phòng")
      }
    }
  }
}

@Composable
fun RoomImageCarousel(imageList: List<Int>) {
  val listState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()

  var currentIndex by remember { mutableIntStateOf(0) }

  Box(modifier = Modifier
    .fillMaxWidth()
    .height(220.dp)
  ) {
    // LazyRow hiển thị ảnh
    LazyRow(
      state = listState,
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp)
    ) {
      items(imageList.size) { index ->
        Image(
          painter = painterResource(id = imageList[index]),
          contentDescription = null,
          modifier = Modifier
            .width(290.dp)
            .height(350.dp)
            .padding(horizontal = 8.dp)
        )
      }
    }

    // 👈 Nút Trái
    IconButton(
      onClick = {
        currentIndex = (currentIndex - 1 + imageList.size) % imageList.size
        coroutineScope.launch {
          listState.animateScrollToItem(currentIndex)
        }
      },
      modifier = Modifier
        .align(Alignment.CenterStart)
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Trái"
      )
    }

    // 👉 Nút Phải
    IconButton(
      onClick = {
        currentIndex = (currentIndex + 1) % imageList.size
        coroutineScope.launch {
          listState.animateScrollToItem(currentIndex)
        }
      },
      modifier = Modifier
        .align(Alignment.CenterEnd)
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = "Phải"
      )
    }
  }
}
