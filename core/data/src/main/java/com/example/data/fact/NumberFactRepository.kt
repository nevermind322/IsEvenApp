package com.example.data.fact

import com.example.data.toRepoResult
import com.example.model.NumberFact
import com.example.network.even.NetworkEvenDataSource
import com.example.network.fact.NetworkFactDatasource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumberFactRepository @Inject constructor(private val dataSource: NetworkFactDatasource) {
    suspend fun getFact(number: Int, factType: NumberFact.FactType) =
        dataSource.getFact(number, factType).toRepoResult()

}