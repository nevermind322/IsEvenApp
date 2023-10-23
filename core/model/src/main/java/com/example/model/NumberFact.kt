package com.example.model

import kotlin.random.Random

class NumberFact(val number: Int, val fact: String, val factType: FactType) {
    enum class FactType {
        TRIVIA, MATH, DATE, YEAR;

        companion object {
            fun getRandomType(): FactType = when (Random.nextInt(from = 0, until = 4)) {
                0 -> TRIVIA
                1 -> MATH
                2 -> DATE
                3 -> YEAR
                else -> TRIVIA
            }

            fun getRandomTypeExceptDate(): FactType = when (val fact = getRandomType()) {
                DATE -> TRIVIA
                else -> fact
            }
        }
    }
}