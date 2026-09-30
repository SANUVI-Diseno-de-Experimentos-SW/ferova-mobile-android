package com.example.pe.edu.upc.ferova_mobile_android.domain.model

data class AchievementProgress(
    val points: Int,
    val currentStreak: Int,
    val bestStreak: Int,
    val healthStatus: String
)
