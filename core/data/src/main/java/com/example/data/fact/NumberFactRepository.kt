package com.example.data.fact

import com.example.model.NumberFact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumberFactRepository @Inject constructor(private val dataSource: NetworkFactDatasource) {

    suspend fun getFact(number: Int, factType: NumberFact.FactType) = dataSource.getFact(number, factType)


}