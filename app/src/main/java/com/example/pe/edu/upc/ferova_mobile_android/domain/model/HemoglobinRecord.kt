package com.example.pe.edu.upc.ferova_mobile_android.domain.model

data class HemoglobinRecord(
    val date: String,
    val value: Float,
    val unit: String = "g/dL"
)
