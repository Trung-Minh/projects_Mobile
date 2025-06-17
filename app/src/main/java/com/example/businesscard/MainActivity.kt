package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BusinessCardTheme {
        Surface(
          modifier = Modifier
            .fillMaxSize()
        ) {
          Greeting()
        }
      }
    }
  }
}

@Composable
fun ContactRow(iconId: Int, text: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 60.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(id = iconId),
      contentDescription = null,
      tint = Color(0xFF015A16),
      modifier = Modifier.size(24.dp) // đảm bảo icon đều size
    )

    Spacer(modifier = Modifier.width(30.dp)) // khoảng cách cố định

    Text(
      text = text,
      modifier = Modifier.weight(1f) // text chiếm phần còn lại
    )
  }
}

@Composable
fun Greeting() {
  Column(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxSize()
  ) {
    Column(
      modifier = Modifier
        .weight(2f)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.android_logo),
        contentDescription = null,
        modifier = Modifier
          .size(130.dp)
          .background(Color(0xFF073041))
      )
      Text(
        text = "Lê Minh Trung",
        fontSize = 50.sp,
        modifier = Modifier
          .padding(top = 5.dp, bottom = 10.dp),
        fontWeight = FontWeight.Bold
      )
      Text(
        text = "Android Developer Extraordinaire",
        fontWeight = FontWeight.Bold,
        color = Color(0xFF015A16),
        fontSize = 17.sp
      )
    }
    Column(
      modifier = Modifier
        .weight(1f)
        .fillMaxSize()
        .padding(bottom = 40.dp),
      verticalArrangement = Arrangement.Bottom
    ) {
      ContactRow(R.drawable.call_24px, "+84 123 456 789")
      ContactRow(R.drawable.share_24px, "@TrungDev")
      ContactRow(R.drawable.mail_24px, "trungb2203482@student.ctu.edu.vn")
    }

  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  BusinessCardTheme {
    Greeting()
  }
}