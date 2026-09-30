package com.example.pe.edu.upc.ferova_mobile_android.domain.repository

import com.example.pe.edu.upc.ferova_mobile_android.domain.model.AchievementProgress
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.Badge


interface AchievementRepository {
    suspend fun getAchievementProgress(patientId: String): AchievementProgress
    suspend fun getBadges(patientId: String): List<Badge>
}
