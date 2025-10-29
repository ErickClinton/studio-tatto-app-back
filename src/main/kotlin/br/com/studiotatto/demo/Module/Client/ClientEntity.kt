package br.com.studiotatto.demo.Module.Client

import br.com.studiotatto.demo.Core.User.UserEntity
import br.com.studiotatto.demo.Module.Address.AddressEntity
import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "clients")
class ClientEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", nullable = false)
    var address: AddressEntity,

    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate,

    @Column(name = "preferences", columnDefinition = "TEXT")
    var preferences: String? = null,

    @Column(name = "tattoo_history", columnDefinition = "TEXT")
    var tattooHistory: String? = null,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {

    constructor() : this(
        id = null,
        user = UserEntity(),
        address = AddressEntity(),
        birthDate = LocalDate.now(),
        preferences = null,
        tattooHistory = null
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
