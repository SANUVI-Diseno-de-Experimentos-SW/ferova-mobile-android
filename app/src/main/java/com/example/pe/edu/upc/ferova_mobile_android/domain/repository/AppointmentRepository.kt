package com.example.pe.edu.upc.ferova_mobile_android.domain.repository

import com.example.pe.edu.upc.ferova_mobile_android.domain.model.Patient
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.appointments.Appointment
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.appointments.HealthCenter
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.appointments.TimeSlot
import java.time.LocalDate


interface AppointmentRepository {
    suspend fun loadNearbyFacilities(lat: Double, lng: Double): List<HealthCenter>
    suspend fun getCenterById(id: String): HealthCenter?
    suspend fun getPatients(): List<Patient>
    suspend fun loadAvailableSlots(centerId: String, date: LocalDate): List<TimeSlot>
    suspend fun bookAppointment(
        centerId: String,
        centerName: String,
        patientId: String,
        patientName: String,
        date: LocalDate,
        time: String
    ): String?

    suspend fun getNextAppointment(): Appointment?
    suspend fun getAppointmentHistory(patientId: String): List<Appointment>
    suspend fun cancelAppointment(appointmentId: String): String?
}