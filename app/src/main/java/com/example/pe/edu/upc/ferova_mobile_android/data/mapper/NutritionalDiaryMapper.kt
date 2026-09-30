package com.example.pe.edu.upc.ferova_mobile_android.data.mapper

import android.util.Log
import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.nutrition.*
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.nutrition.RegisterFoodEntryResult


// ═══════════════════════════════════════════════════════════════════════════
// REGISTRO DE ALIMENTO (POST /food-entry)
// ═══════════════════════════════════════════════════════════════════════════

fun FoodEntryResponse.toDomain(): RegisterFoodEntryResult {

    return RegisterFoodEntryResult(
        success = success,
        message = message,
        foodEntry = foodEntry.toDomain(),
        newTotalIronAbsorbed = newTotalIronAbsorbed,
        warningMessage = warningMessage
    )
}

fun FoodEntryDto.toDomain(): FoodEntry {

    Log.d(
        "FOOD_DEBUG",
        "entryId=$entryId foodName=$foodName"
    )

    return FoodEntry(
        entryId = entryId ?: "",
        foodName = foodName,
        quantity = quantity,
        unit = unit,
        ironAbsorbed = ironAbsorbed,
        isInhibitor = isInhibitor
    )
}

// ═══════════════════════════════════════════════════════════════════════════
// DIARIO DE HOY (GET /today/{patientId})
// ═══════════════════════════════════════════════════════════════════════════

fun TodayDiaryResponse.toDomain(): TodayDiary {
    return TodayDiary(
        diaryId = diaryId,
        date = date,
        totalIronAbsorbed = this.totalIronAbsorbed,
        foodEntries = this.foodEntries.map { it.toDomain() }
    )
}

fun FoodEntryItemDto.toDomain(): FoodEntry {
    return FoodEntry(
        entryId = entryId,
        foodName = foodName,
        quantity = quantity,
        unit = unit,
        ironAbsorbed = ironAbsorbed,
        isInhibitor = isInhibitor
    )
}

// ═══════════════════════════════════════════════════════════════════════════
// BÚSQUEDA Y CATEGORÍAS (GET /foods/search & /foods/category)
// ═══════════════════════════════════════════════════════════════════════════

fun SearchFoodResponse.toDomain(): SearchFoodResult {
    return SearchFoodResult(
        searchText = this.searchText,
        resultCount = this.resultCount,
        items = items.map { it.toDomain() }
    )
}

fun CategoryFoodResponse.toDomain(): CategoryFood {
    return CategoryFood(
        category = this.category,
        items = items.map { it.toDomain() }
    )
}

fun FoodItemSummaryDto.toDomain(): FoodItem {
    return FoodItem(
        foodItemId = foodItemId,
        name = name,
        ironType = ironType,
        ironMgPer100g = ironMgPer100g,
        isInhibitor = isInhibitor
    )
}

// ═══════════════════════════════════════════════════════════════════════════
// DETALLES DE ALIMENTO (GET /foods/{foodItemId})
// ═══════════════════════════════════════════════════════════════════════════

fun FoodItemDetailsResponse.toDomain(): FoodItemDetails {
    return FoodItemDetails(
        foodItemId = foodItemId,
        name = name,
        ironType = ironType,
        ironMgPer100g = ironMgPer100g,
        isInhibitor = isInhibitor,
        warningMessage = warningMessage,
        defaultUnit = defaultUnit
    )
}

// ═══════════════════════════════════════════════════════════════════════════
// HISTORIAL NUTRICIONAL (GET /history/{patientId})
// ═══════════════════════════════════════════════════════════════════════════

fun NutritionalHistoryResponse.toDomain(): NutritionalHistory {
    return NutritionalHistory(
        patientId = patientId,
        period = period.toDomain(),
        days = days.map { it.toDomain() }
    )
}

fun PeriodDto.toDomain(): HistoryPeriod {
    return HistoryPeriod(
        startDate = this.startDate,
        endDate = this.endDate
    )
}

fun DaySummaryDto.toDomain(): DaySummary {
    return DaySummary(
        date = date,
        displayDate = displayDate,
        totalIronAbsorbed = totalIronAbsorbed,
        hasInhibitor = hasInhibitor,
        inhibitorCount = inhibitorCount,
        totalFoodEntries = totalFoodEntries
    )
}