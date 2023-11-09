package com.example.domain

import android.graphics.Bitmap
import com.example.data.RepoResult
import com.example.data.even.EvenRepository
import com.example.digitClassifier.DigitClassifier
import javax.inject.Inject

class ClassifyEvenUseCase @Inject constructor(
    private val evenRepo: EvenRepository, private val classifier: DigitClassifier
) {

    suspend operator fun invoke(bitmap: Bitmap) =
        try {
            val number = classifier.classify(bitmap)
            evenRepo.isEven(number)
        } catch (e: Exception) {
            RepoResult.Error(e.message ?: "Unknown error")
        }
}