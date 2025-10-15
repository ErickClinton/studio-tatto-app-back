package br.com.studiotatto.demo.Core.Style

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "styles")
class StyleTattoEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "name", nullable = false, unique = true)
    var name: String = "",

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {
    constructor() : this(
        null,
        "",
        null
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
