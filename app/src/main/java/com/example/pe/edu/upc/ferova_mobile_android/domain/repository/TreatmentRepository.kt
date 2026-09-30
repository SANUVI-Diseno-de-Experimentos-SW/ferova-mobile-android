package com.example.pe.edu.upc.ferova_mobile_android.domain.repository

import com.example.pe.edu.upc.ferova_mobile_android.domain.model.*


interface TreatmentRepository {
    suspend fun getTodayDose(patientId: String): TodayDose
    suspend fun getDoseHistory(patientId: String): DoseHistory
    suspend fun confirmDose(patientId: String): DoseRecord
}
