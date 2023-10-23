package com.example.network.even


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitEvenDataSource @Inject constructor(private val api: IsEvenApiService) :
    NetworkEvenDataSource {

    override suspend fun isEven(number: Int) = withContext(Dispatchers.IO) {
        api.isEven(number)
    }

}