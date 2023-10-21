package com.example.data

import com.example.model.IsEven

interface NetworkEvenDataSource {
    suspend fun isEven(number: Int) : RepoResult<IsEven>
}