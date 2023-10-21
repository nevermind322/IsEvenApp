package com.example.data.even

import com.example.data.RepoResult
import com.example.model.IsEven

interface NetworkEvenDataSource {
    suspend fun isEven(number: Int) : RepoResult<IsEven>
}