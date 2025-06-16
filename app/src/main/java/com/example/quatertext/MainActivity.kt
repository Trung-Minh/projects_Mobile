package com.example.quatertext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quatertext.ui.theme.QuaterTextTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      QuaterTextTheme {
        Surface {
          Greeting()
        }
      }
    }
  }
}

@Composable
fun Greeting() {
  Column (
    modifier = Modifier
      .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Row (
      modifier = Modifier
        .weight(1f)
    ) {
      Column (
        modifier = Modifier
          .background(Color(0xFFEADDFF))
          .weight(1f)
          .fillMaxSize()
          .padding(16.dp)
      ) {
        Text(
          text = "Text composable",
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .padding(16.dp)
        )
        Text(
          text = stringResource(R.string.text1)
        )
      }
      Column (
        modifier = Modifier
          .background(Color(0xFFD0BCFF))
          .weight(1f)
          .fillMaxSize()
          .padding(16.dp)
      ){
        Text(
          text = "Image composable",
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .padding(16.dp)
        )
        Text(
          text = stringResource(R.string.text2)
        )
      }
    }
    Row (
      modifier = Modifier
        .weight(1f)
    ){
      Column (
        modifier = Modifier
          .background(Color(0xFFB69DF8))
          .weight(1f)
          .fillMaxSize()
          .padding(16.dp)
      ) {
        Text(
          text = "Row composable",
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .padding(16.dp)
        )
        Text(
          text = stringResource(R.string.text3)
        )
      }
      Column(
        modifier = Modifier
          .background(Color(0xFFF6EDFF))
          .weight(1f)
          .fillMaxSize()
          .padding(16.dp)
      ) {
        Text(
          text = "Column composable",
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .padding(16.dp)
        )
        Text(
          text = stringResource(R.string.text4)
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  QuaterTextTheme {
    Greeting()
  }
}