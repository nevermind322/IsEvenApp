package com.example.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsEvenDataSource @Inject constructor(private val api: IsEvenApiService) {

    suspend fun isEven(number: Int) = withContext(Dispatchers.IO) { api.isEven(number) }

}