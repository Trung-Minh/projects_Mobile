package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      LearnTogetherTheme {
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
fun Greeting() {
  Column {
    Image(
      painter = painterResource(
        id = R.drawable.bg_compose_background,
      ),
      contentDescription = "Ảnh Background"
    )
    Text(
      text = "Jetpack Compose tutorial",
      fontSize = 24.sp,
      modifier = Modifier
        .padding(6.dp)
    )

    Text(
      text = stringResource(R.string.doan1),
      modifier = Modifier
        .padding(top = 16.dp, bottom = 16.dp)
    )

    Text(
      text = stringResource(R.string.doan2),
      modifier = Modifier
        .padding(16.dp)
    )
  }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  LearnTogetherTheme {
    Greeting()
  }
}