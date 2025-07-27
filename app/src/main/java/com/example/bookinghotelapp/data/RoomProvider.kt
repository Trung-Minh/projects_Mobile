package com.example.bookinghotelapp.data

import android.content.Context
import com.example.bookinghotelapp.R
import com.example.bookinghotelapp.model.RoomType

fun provideRoomList(context: Context): List<RoomType> {
  return listOf(
    RoomType(
      id = 1,
      name = "Standard Room",
      description = context.getString(R.string.standard_description),
      price = 500.0,
      availableRooms = 5,
      imageList = listOf(
        R.drawable.standard_1,
        R.drawable.standard_2,
        R.drawable.standard_3,
        R.drawable.standard_4
      )
    ),
    RoomType(
      id = 2,
      name = "Deluxe",
      description = context.getString(R.string.deluxe_description),
      price = 700.0,
      availableRooms = 3,
      imageList = listOf(
        R.drawable.deluxe_1,
        R.drawable.deluxe_2,
        R.drawable.deluxe_3,
        R.drawable.deluxe_4
      )
    ),
    RoomType(
      id = 3,
      name = "Suite",
      description = context.getString(R.string.suite_description),
      price = 1000.0,
      availableRooms = 2,
      imageList = listOf(
        R.drawable.suite_1,
        R.drawable.suite_2,
        R.drawable.suite_3,
        R.drawable.suite_4
      )
    ),
    RoomType(
      id = 4,
      name = "Executive Room",
      description = context.getString(R.string.executive_description),
      price = 1200.0,
      availableRooms = 4,
      imageList = listOf(
        R.drawable.executive_1,
        R.drawable.executive_2,
        R.drawable.executive_3,
        R.drawable.executive_4
      )
    ),
    RoomType(
      id = 5,
      name = "Family Room",
      description = context.getString(R.string.family_description),
      price = 800.0,
      availableRooms = 3,
      imageList = listOf(
        R.drawable.family_1,
        R.drawable.family_2,
        R.drawable.family_3,
        R.drawable.family_4
      )
    )
  )
}