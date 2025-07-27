package com.example.bookinghotelapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookinghotelapp.model.RoomType

class RoomViewModel : ViewModel() {
  var rooms by mutableStateOf(listOf<RoomType>())
    private set

  var selectedRoom by mutableStateOf<RoomType?>(null)
  var quantity by mutableIntStateOf(1)

  fun updateRooms(list: List<RoomType>) {
    rooms = list
  }

  fun selectRoom(room: RoomType) {
    selectedRoom = room
    quantity = 1
  }

  fun updateQuantity(q: Int) {
    quantity = q
  }
}

