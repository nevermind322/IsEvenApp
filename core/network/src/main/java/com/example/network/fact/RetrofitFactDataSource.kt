package com.example.network.fact

import com.example.data.RepoResult
import com.example.data.fact.NetworkFactDatasource
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
        number: Int,
        factType: NumberFact.FactType
    ): RepoResult<NumberFact> = withContext(Dispatchers.IO) {
        when (val res = service.getFact(number, factType.toPath())) {
            is ApiResult.Success -> RepoResult.Success(NumberFact(number, res.data, factType))
            is ApiResult.Error -> RepoResult.Error(res.msg)
            is ApiResult.Exception -> RepoResult.Error(res.e.message ?: "Unknown error")
        }
    }
}


fun NumberFact.FactType.toPath(): String = when (this) {
    NumberFact.FactType.TRIVIA -> ""
    else -> this.name.lowercase()
}