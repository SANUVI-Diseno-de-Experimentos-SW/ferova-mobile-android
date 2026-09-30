package com.example.pe.edu.upc.ferova_mobile_android.domain.model.communication

data class Nurse(
    val id: String,
    val name: String,
    val specialty: String = "Enfermera asignada",
    val email: String = "",
    val isOnline: Boolean = false
)
