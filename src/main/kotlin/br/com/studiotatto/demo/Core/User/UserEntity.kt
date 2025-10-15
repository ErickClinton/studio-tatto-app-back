package br.com.studiotatto.demo.Core.User

import br.com.studiotatto.demo.Core.Role.RoleEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.*


@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "email")
    var email: String = "",

    @Column(name = "password")
    var password: String = "",

    @Column(name = "phone", length = 13, nullable = false)
    var phone: String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    var role: RoleEntity,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {
    constructor(): this(id = null, name = "", email = "", password = "", phone = "", role = RoleEntity())
    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}