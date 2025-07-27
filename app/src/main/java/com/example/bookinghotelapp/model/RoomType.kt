package com.example.bookinghotelapp.model

data class RoomType(
  val id: Int,
  val name: String,
  val description: String,
  val price: Double,
  val availableRooms: Int,
  val imageList: List<Int>
)