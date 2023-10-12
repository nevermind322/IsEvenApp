package com.example.model

import com.squareup.moshi.Json


data class IsEven(@Json(name = "iseven") val isEven : Boolean, val ad : String)