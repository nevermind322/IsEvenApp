package com.example.data.fact

import com.example.data.RepoResult
import com.example.model.NumberFact

interface NetworkFactDatasource
{
    suspend fun getFact(number: Int, factType: NumberFact.FactType) :RepoResult<NumberFact>
}