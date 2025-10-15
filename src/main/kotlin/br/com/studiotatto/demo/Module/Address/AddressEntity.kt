package br.com.studiotatto.demo.Module.Address

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "addresses")
class AddressEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "logradouro", nullable = false)
    var logradouro: String = "",

    @Column(name = "cep", nullable = false, length = 9)
    var cep: String = "",

    @Column(name = "numero", nullable = false)
    var numero: String = "",

    @Column(name = "complemento")
    var complemento: String? = null,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {

    constructor() : this(
        id = null,
        logradouro = "",
        cep = "",
        numero = "",
        complemento = null
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
