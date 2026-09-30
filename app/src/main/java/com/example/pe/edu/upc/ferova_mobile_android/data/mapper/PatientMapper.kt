package com.example.pe.edu.upc.ferova_mobile_android.data.mapper

import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.PatientResponse
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.Patient


fun PatientResponse.toDomain(): Patient = Patient(
    id        = id,
    name      = name,
    lastName  = lastName   ?: "",
    birthDate = birthDate  ?: "",
    gender    = gender     ?: "",
    weight    = weight     ?: 0.0,
    height    = height     ?: 0.0,
    motherId  = motherId   ?: ""
)


