package com.example.pe.edu.upc.ferova_mobile_android.data.mapper


import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.DoseRecordDto
import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.TodayDoseDto
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.DoseRecord
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.TodayDose
import java.util.UUID

fun TodayDoseDto.toDomain(patientId: String): TodayDose = TodayDose(
    patientId = patientId,
    canConfirm = canConfirm ?: false,
    scheduledTime = dosingHours ?: "",
    confirmedAt = if (status == "CONFIRMED") "confirmed" else null,
    dosingHours = dosingHours ?: ""
)
fun DoseRecordDto.toDomain(): DoseRecord = DoseRecord(
    id = id ?: UUID.randomUUID().toString(),
    treatmentId = treatmentId ?: "",
    scheduledDate = scheduledDate ?: "",
    confirmedAt = confirmedAt,
    status = status ?: "PENDING",
    hoursWithoutConfirmation = hoursWithoutConfirmation ?: 0
)