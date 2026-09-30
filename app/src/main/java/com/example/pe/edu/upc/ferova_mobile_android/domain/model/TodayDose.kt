package com.example.pe.edu.upc.ferova_mobile_android.domain.model

data class TodayDose(
    val patientId: String,
    val canConfirm: Boolean,
    val scheduledTime: String,
    val confirmedAt: String? = null,
    val dosingHours: String
)
