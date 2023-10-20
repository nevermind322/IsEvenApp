package com.example.network.fact

import com.example.model.NumberFact
import com.example.network.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumberFactDataSource @Inject constructor(private val service: NumbersApiService) {

    suspend operator fun invoke(
        number: Int,
        factType: NumberFact.FactType = NumberFact.FactType.TRIVIA
    ) : ApiResult<NumberFact> = withContext(Dispatchers.IO) {
        when (val res = service.getFact(number, factType.toPath())) {
            is ApiResult.Success -> ApiResult.Success(NumberFact(number, res.data, factType))
            else -> res as ApiResult<NumberFact>
        }
    }
}


fun NumberFact.FactType.toPath(): String = this.name.lowercase()