package com.example.pe.edu.upc.ferova_mobile_android.data.mapper

import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.AchievementProgress
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.Badge
import kotlin.math.min

fun AchievementProgressDto.toDomain(): AchievementProgress = AchievementProgress(
    points        = totalPoints   ?: 0,
    currentStreak = currentStreak ?: 0,
    bestStreak    = longestStreak ?: 0,
    healthStatus  = status        ?: ""
)

fun BadgeDto.toDomain(currentStreak: Int = 0): Badge {
    val targetDays = milestone ?: daysNeeded ?: 1

    val completedDays = when {
        isUnlocked == true -> targetDays
        progress != null && progress > 0 -> (progress * targetDays / 100).coerceIn(0, targetDays)
        else -> min(currentStreak, targetDays)
    }

    return Badge(
        id              = id ?: "",
        name            = name ?: "",
        description     = description ?: "",
        isUnlocked      = isUnlocked ?: false,
        currentProgress = completedDays,
        targetProgress  = targetDays,
        unlockedAt      = unlockedAt,
        category        = type ?: ""
    )
}