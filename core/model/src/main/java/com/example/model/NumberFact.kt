package com.example.model

class NumberFact(val number : Int, val fact : String, val factType : FactType) {
    enum class FactType{
        TRIVIA, MATH, DATE, YEAR
    }
}