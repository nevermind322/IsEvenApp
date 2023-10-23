package com.example.network.fact

import com.example.model.NumberFact
import com.example.network.ApiResult

interface NetworkFactDatasource
{
    suspend fun getFact(number: Int, factType: NumberFact.FactType) :ApiResult<NumberFact>
}