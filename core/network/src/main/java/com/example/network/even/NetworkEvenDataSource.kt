package com.example.network.even

import com.example.model.IsEven
import com.example.network.ApiResult

interface NetworkEvenDataSource {
    suspend fun isEven(number: Int) : ApiResult<IsEven>
}