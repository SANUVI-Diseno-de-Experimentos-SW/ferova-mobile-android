package com.example.pe.edu.upc.ferova_mobile_android.domain.repository

import com.example.pe.edu.upc.ferova_mobile_android.domain.model.HemoglobinRecord
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.Patient


interface PatientRepository {
    suspend fun getMyPatients(): List<Patient>
    suspend fun registerPatient(patient: Patient): Boolean
    suspend fun getHemoglobinEvolution(patientId: String): List<HemoglobinRecord>

}
