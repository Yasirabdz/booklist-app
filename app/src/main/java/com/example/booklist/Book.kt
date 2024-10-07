package com.example.booklist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val name: String,
    val description: String,
    val photo: Int,
    val synopsis: String
) : Parcelable