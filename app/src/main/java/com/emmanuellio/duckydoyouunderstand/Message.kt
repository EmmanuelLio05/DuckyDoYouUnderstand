package com.emmanuellio.duckydoyouunderstand

import androidx.annotation.DrawableRes

data class Message(
    val owner: Owner,
    val message: String? = null,
    @DrawableRes val content: Int? = null,
    val time: Long
)