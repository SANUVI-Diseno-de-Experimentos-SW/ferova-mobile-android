package com.example.pe.edu.upc.ferova_mobile_android.domain.model.communication


data class Consultation(
    val id: String,
    val patientId: String,
    val patientName: String,
    val nurse: Nurse,
    val isOpen: Boolean = true,
    val messages: List<Message> = emptyList()
) {
    val lastMessage: String
        get() = messages.lastOrNull()?.text ?: "Sin mensajes"
}
