package com.example.pe.edu.upc.ferova_mobile_android.data.mapper

import android.util.Log
import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.communication.*
import com.example.pe.edu.upc.ferova_mobile_android.domain.model.communication.PatientWithNurse


private const val TAG = "ConsultationMapper"

fun PatientWithNurseDto.toDomain(): PatientWithNurse = PatientWithNurse(
    patientId = patientId ?: "",
    patientName = patientName ?: "Paciente",
    hasNurse = hasNurse,
    nurse = if (hasNurse && !nurseId.isNullOrBlank()) {
        Nurse(
            id = nurseId,
            name = nurseName ?: "Enfermera asignada"
        )
    } else null
)

fun NurseInfoResponse.toDomain(): Nurse? =
    if (hasNurse && !nurseId.isNullOrBlank()) {
        Nurse(
            id = nurseId,
            name = nurseName ?: "Enfermera asignada",
            specialty = specialty ?: "Enfermería pediátrica",
            email = nurseEmail ?: ""
        )
    } else null
fun ConsultationResponse.toDomain(): Consultation? {
    val consultationId = consultationId
    if (consultationId.isNullOrBlank()) {
        Log.e(TAG, "toDomain: Consultation ID is null or empty")
        return null
    }

    Log.d(TAG, "toDomain: id=$consultationId, messages size=${messages?.size ?: 0}")

    val nurse = if (!nurseId.isNullOrBlank()) {
        Nurse(
            id = nurseId,
            name = nurseName ?: "Enfermera",
            specialty = "Enfermería pediátrica"
        )
    } else {
        Nurse(
            id = "unknown",
            name = "Enfermera asignada",
            specialty = "Enfermería pediátrica"
        )
    }

    // ✅ Procesar mensajes del chat (si vienen)
    val messages = mutableListOf<Message>()
    this.messages?.mapNotNull {
        try {
            it.toDomain()
        } catch (e: Exception) {
            Log.e(TAG, "Error mapping message: $it", e)
            null
        }
    }?.let { messages.addAll(it) }

    // Si no hay mensajes pero hay lastMessage, usarlo como fallback
    if (messages.isEmpty() && !lastMessage.isNullOrBlank()) {
        val message = Message(
            id = "msg_${System.currentTimeMillis()}",
            text = lastMessage,
            isFromNurse = lastMessageSenderRole == "NURSE",
            time = lastMessageDate.toHourMinute()
        )
        messages.add(message)
    }

    return Consultation(
        id = consultationId,
        patientId = patientId ?: "",
        patientName = patientName ?: "Paciente",
        nurse = nurse,
        isOpen = true,
        messages = messages  // ✅ Aquí se asignan los mensajes
    )
}

// ✅ CAMBIO: usar sentAt en lugar de timestamp
fun MessageDto.toDomain(): Message {
    val sender = senderRole ?: "UNKNOWN"
    val contentText = content ?: ""
    val time = sentAt.toHourMinute()

    Log.d(TAG, "MessageDto.toDomain: id=$id, senderRole=$sender, content=${contentText.take(20)}")

    return Message(
        id = id ?: "",
        text = contentText,
        isFromNurse = sender == "NURSE",
        time = time
    )
}

private fun String?.toHourMinute(): String {
    if (this.isNullOrBlank()) return ""
    val t = substringAfter('T', "")
    return if (t.length >= 5) t.substring(0, 5) else takeLast(5)
}