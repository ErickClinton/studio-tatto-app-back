package br.com.studiotatto.demo.Shared.AvailabilityStatus

import java.util.*

interface IAVailabilityStatusContract {
    fun findById(id:UUID) : AvailabilityStatusEntity?
}