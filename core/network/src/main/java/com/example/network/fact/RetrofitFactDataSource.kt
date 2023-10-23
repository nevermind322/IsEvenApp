package com.example.network.fact

import com.example.model.NumberFact
import com.example.network.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitFactDataSource @Inject constructor(private val service: NumbersApiService) :
    NetworkFactDatasource {
    override suspend fun getFact(
        number: Int, factType: NumberFact.FactType
    ): ApiResult<NumberFact> = withContext(Dispatchers.IO) {
        val fact = service.getFact(number, factType.toPath())
        if (fact is ApiResult.Success)
            ApiResult.Success(NumberFact(number, fact.data, factType))
        else
            fact as ApiResult<NumberFact>
    }
}

internal fun NumberFact.FactType.toPath(): String = when (this) {
    NumberFact.FactType.TRIVIA -> ""
    else -> this.name.lowercase()
}