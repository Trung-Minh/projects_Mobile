package com.example.artwork

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artwork.ui.theme.ArtWorkTheme


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ArtWorkTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
          ArtWorkScreen()
        }
      }
    }
  }
}

data class ArtWork(
  val imageID: Int,
  val title: String,
  val artist: String,
)

@Composable
fun ArtWorkScreen() {
  val artWork = listOf(
    ArtWork(R.drawable.raiden_shogun_1, "Raiden Shogun", "Nhân vật ở Inazuma"),
    ArtWork(R.drawable.yaemiko, "Yae Miko", "Thường thì ở đền Narukami"),
    ArtWork(R.drawable.neuvillette, "Neuvillette", "Nhân vật ở Fontaine")
  )
  var index by remember { mutableIntStateOf(0) }
  val currentArt = artWork[index]

  ArtWorkBody(
    artWork = currentArt,
    onPrevious = {
      index = (index - 1 + artWork.size) % artWork.size
    },
    onNext = {
      index = (index + 1) % artWork.size
    }
  )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ArtWorkBody(
  artWork: ArtWork,
  onPrevious: () -> Unit,
  onNext: () -> Unit,
) {
  BoxWithConstraints(
    modifier = Modifier.fillMaxSize()
  ) {
    val isLargeScreen = maxWidth > 600.dp
    val contentPadding = if (isLargeScreen) 32.dp else 16.dp
    val imageSize = if (isLargeScreen) 600.dp else 250.dp
    val textSize = if (isLargeScreen) 50.sp else 30.sp
    val text = if (isLargeScreen) 36.sp else 15.sp
    val layoutWidth = if (isLargeScreen) 700.dp else Dp.Unspecified

    Column(
      modifier = Modifier
        .fillMaxSize()
        .widthIn(max = layoutWidth),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Spacer(modifier = Modifier.height(24.dp))

      if (isLargeScreen) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .weight(6f),
        ) {
          Image(
            painter = painterResource(id = artWork.imageID),
            contentDescription = null,
            modifier = Modifier
              .weight(3f)
              .fillMaxHeight()
              .padding(end = 8.dp)
              .border(1.dp, Color.Black)
          )
          Column(
            modifier = Modifier
              .weight(3f)
              .background(Color.LightGray)
              .padding(8.dp),
            verticalArrangement = Arrangement.Center
          ) {
            Text(
              text = artWork.title,
              fontWeight = FontWeight.Bold,
              fontSize = textSize
            )
            Text(
              text = artWork.artist,
              modifier = Modifier.padding(horizontal = 8.dp),
              fontSize = text
            )
          }
        }
      } else {
        Image(
          painter = painterResource(id = artWork.imageID),
          contentDescription = null,
          modifier = Modifier
            .fillMaxWidth()
            .size(imageSize)
            .padding(contentPadding)
            .border(1.dp, Color.Black)
            .weight(4f)
        )
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding)
            .weight(2f)
            .background(Color.LightGray),
          horizontalAlignment = Alignment.Start,
        ) {
          Text(
            text = artWork.title,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = textSize
          )
          Text(
            text = artWork.artist,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = text
          )
        }
      }
      Spacer(modifier = Modifier.height(32.dp))
      Row(
        modifier = Modifier
          .weight(2f)
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        Button(
          onClick = onPrevious,
          modifier = Modifier
            .padding(8.dp)
            .weight(1f)
        ) {
          Text("Previous")
        }
        Button(
          onClick = onNext,
          modifier = Modifier
            .padding(8.dp)
            .weight(1f)
        ) {
          Text("Next")
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ArtWorkPreview() {
  ArtWorkTheme {
    ArtWorkScreen()
  }
}