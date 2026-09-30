package com.example.pe.edu.upc.ferova_mobile_android.domain.model.appointments

data class TimeSlot(
    val time: String,        // "08:00", "09:00"
    val isAvailable: Boolean
)