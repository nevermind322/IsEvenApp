package com.example.domain

import android.graphics.Bitmap
import android.util.Log
import com.example.data.RepoResult
import com.example.data.even.EvenRepository
import com.example.digitClassifier.DigitClassifier
import com.example.model.IsEven
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassifyEvenUseCase @Inject constructor(
    private val evenRepo: EvenRepository, private val classifier: DigitClassifier
) {

    suspend operator fun invoke(bitmap: Bitmap) =
        try {
            Log.d("classifier", "use case invoked")
            val number = classifier.classify(bitmap)
            val res = evenRepo.isEven(number)
            if (res is RepoResult.Success)
                RepoResult.Success(NumberIsEven(number, res.data))
            else res as RepoResult<NumberIsEven>
        } catch (e: Exception) {
            RepoResult.Error(e.message ?: "Unknown error")
        }
}

data class NumberIsEven(val number: Int, val isEven: IsEven)

