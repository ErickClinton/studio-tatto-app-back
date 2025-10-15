package br.com.studiotatto.demo.Shared.AvailabilityStatus

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "availability_status")
class AvailabilityStatusEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "status", nullable = false, unique = true)
    var status: String = "",

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {
    constructor() : this(
        id = null,
        status = "",
        description = null
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
