package br.com.studiotatto.demo.Core.Role

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import jakarta.persistence.*

@Entity
@Table(name = "roles")
class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true)
    var roleName: RoleEnum
) {
    constructor(): this(0, RoleEnum.USER)
}