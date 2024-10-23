package com.emmanuellio.duckydoyouunderstand

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Ducky(
    speech: MutableState<String?>,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(
            when(speech.value?.length){
                in 0..25 -> R.drawable.very_happy
                in 26..50 -> R.drawable.happy
                in 51..75 -> R.drawable.worried
                in 76..Int.MAX_VALUE -> R.drawable.stressed
                else -> R.drawable.cool
            }
        ),
        contentDescription = "An image of a ducky.",
        modifier = modifier
    )
}