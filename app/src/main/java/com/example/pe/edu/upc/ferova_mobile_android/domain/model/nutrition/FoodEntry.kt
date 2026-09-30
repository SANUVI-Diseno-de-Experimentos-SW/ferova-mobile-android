package com.example.pe.edu.upc.ferova_mobile_android.domain.model.nutrition

data class FoodEntry(
    val entryId: String,
    val foodName: String,
    val quantity: Int,
    val unit: String,
    val ironAbsorbed: Double,
    val isInhibitor: Boolean
)

data class RegisterFoodEntryResult(
    val success: Boolean,
    val message: String,
    val foodEntry: FoodEntry,
    val newTotalIronAbsorbed: Double,
    val warningMessage: String?
)
