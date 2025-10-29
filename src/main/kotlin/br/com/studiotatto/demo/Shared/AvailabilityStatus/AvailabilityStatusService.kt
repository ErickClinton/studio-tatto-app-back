package br.com.studiotatto.demo.Shared.AvailabilityStatus

import org.springframework.stereotype.Service
import java.util.*

@Service
class AvailabilityStatusService(private val availabilityStatusRepository: AvailabilityStatusRepository) : IAVailabilityStatusContract {
    override fun findById(id: UUID): AvailabilityStatusEntity? {
        return availabilityStatusRepository.findById(id).orElse(null)
    }
}