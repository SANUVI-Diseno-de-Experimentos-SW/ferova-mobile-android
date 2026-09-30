package com.example.pe.edu.upc.ferova_mobile_android.domain.model.communication

/**
 * Paciente (hijo) de la madre junto con el estado de asignación de su enfermera.
 * Alimenta la pantalla "Nueva Consulta — ¿Sobre quién es tu consulta?".
 */
data class PatientWithNurse(
    val patientId: String,
    val patientName: String,
    val hasNurse: Boolean,
    val nurse: Nurse?
)
