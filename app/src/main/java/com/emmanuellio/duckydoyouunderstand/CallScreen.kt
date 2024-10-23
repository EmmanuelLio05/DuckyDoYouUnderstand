package com.emmanuellio.duckydoyouunderstand

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CallScreen() {
    val speech = rememberSaveable {
        mutableStateOf<String?>(null)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
    ) {

        Text(
            text = ""
        )

        Ducky(
            speech = speech,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(2.dp, Color.Yellow),
                    CircleShape
                )
                .scale(1.12f)
        )

        Row {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color.Green)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dots),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .background(Color.White)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier= Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = null,
                    tint = Color.White,
                    modifier= Modifier
                        .rotate(180f)
                        .background(Color.Red)
                )
            }
        }
    }
}


@Composable
@Preview(device = "id:pixel_6",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
fun CallScreenPreview(){
    CallScreen()
}