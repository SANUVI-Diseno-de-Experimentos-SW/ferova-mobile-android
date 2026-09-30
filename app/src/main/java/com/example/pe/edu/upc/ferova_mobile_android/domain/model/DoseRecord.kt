package com.example.pe.edu.upc.ferova_mobile_android.domain.model

data class DoseRecord(
    val id: String,
    val treatmentId: String,           // ← NUEVO
    val scheduledDate: String,          // ← NUEVO (cambia "date" por "scheduledDate")
    val confirmedAt: String? = null,
    val status: String,                 // "CONFIRMED" | "OMITTED" | "PENDING"
    val hoursWithoutConfirmation: Int = 0  // ← NUEVO
)