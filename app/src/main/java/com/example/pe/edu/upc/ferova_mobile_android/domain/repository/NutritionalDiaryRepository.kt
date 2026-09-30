package com.example.pe.edu.upc.ferova_mobile_android.domain.repository

import com.example.pe.edu.upc.ferova_mobile_android.domain.model.nutrition.*


interface NutritionalDiaryRepository {
    /**
     * Registra el consumo de un alimento
     */
    suspend fun registerFoodEntry(
        patientId: String,
        foodItemId: String,
        quantity: Int
    ): RegisterFoodEntryResult
    /**
     * Obtiene el diario nutricional del día actual
     */
    suspend fun getTodayDiary(patientId: String): TodayDiary
    /**
     * Obtiene alimentos por categoría
     */
    suspend fun getFoodsByCategory(category: String): CategoryFood
    /**
     * Busca alimentos por nombre
     */
    suspend fun searchFoods(text: String): SearchFoodResult
    /**
     * Obtiene detalles de un alimento
     */
    suspend fun getFoodDetail(foodItemId: String): FoodItemDetails
    /**
     * Obtiene el historial nutricional
     */
    suspend fun getNutritionalHistory(
        patientId: String,
        startDate: String? = null,
        endDate: String? = null
    ): NutritionalHistory
}
