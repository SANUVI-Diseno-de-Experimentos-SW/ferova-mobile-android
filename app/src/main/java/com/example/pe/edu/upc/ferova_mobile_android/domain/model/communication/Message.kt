package com.example.pe.edu.upc.ferova_mobile_android.domain.model.communication

data class Message(
    val id: String,
    val text: String,
    val isFromNurse: Boolean,
    val time: String
)
