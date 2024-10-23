package com.emmanuellio.duckydoyouunderstand

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.emmanuellio.duckydoyouunderstand.ui.theme.Green30
import com.emmanuellio.duckydoyouunderstand.ui.theme.Green40
import com.emmanuellio.duckydoyouunderstand.ui.theme.Green50
import com.emmanuellio.duckydoyouunderstand.ui.theme.Surface20
import java.util.Date
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun ChatScreen(
    speech: MutableState<String?>,
    modifier: Modifier = Modifier,
    onMicClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    //TODO Add Messages list state

    LaunchedEffect(speech.value) {
        Log.i("CHAT-SCREEN", "Speech value: ${speech.value}")
        //TODO Add speech.value listener
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(Surface20)
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(56.dp)
                .background(
                    Green40,
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 4.dp,
                        bottomEnd = 4.dp
                    )
                )
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 0.dp)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.sunglasses
                ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan)
                    .border(
                        BorderStroke(2.dp, Color.Yellow),
                        CircleShape
                    )
                    .scale(1.1f)
            )

            Text(
                text = "Patito", // TODO Agregar recursos string
                fontSize = 18.sp,
                color = Color(0xffF5F5F5),
                modifier = Modifier
            )

            IconButton(
                onClick = { },
                modifier = Modifier.background(Color.Transparent)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dots),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                )
            }
        }

        LazyColumn(
            reverseLayout = true,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight(0.92f)
        ) {
            // TODO Use messages items and ChatBox composable
        }


        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            IconButton(
                onClick = {
                    onMicClick()
                },
                modifier = Modifier
                    .size(56.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Green30)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.microphone),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(12.dp)

                )
            }
        }
    }
}

@Composable
fun ChatBox(
    modifier: Modifier = Modifier
) {
    //TODO Create Chatbox composable
}