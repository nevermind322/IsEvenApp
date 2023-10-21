package com.example.domain

import com.example.data.RepoResult
import com.example.data.even.EvenRepository
import com.example.data.fact.NumberFactRepository
import com.example.model.IsEven
import com.example.model.NumberFact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EvenFactUseCase @Inject constructor(
    private val evenRepo: EvenRepository,
    private val factRepo: NumberFactRepository
) {

    suspend operator fun invoke(
        number: Int,
        factType: NumberFact.FactType
    ): RepoResult<FactWithEven> {
        val evenRes = evenRepo.isEven(number)
        val factRes = factRepo.getFact(number, factType)

        return if (factRes is RepoResult.Error || evenRes is RepoResult.Error) RepoResult.Error("Error!")
        else RepoResult.Success(
            FactWithEven(
                (factRes as RepoResult.Success).data,
                (evenRes as RepoResult.Success).data
            )
        )
    }

}

data class FactWithEven(val fact: NumberFact, val even: IsEven)