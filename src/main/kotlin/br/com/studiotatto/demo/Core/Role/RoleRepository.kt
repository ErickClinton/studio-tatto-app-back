package br.com.studiotatto.demo.Core.Role

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<RoleEntity, Long> {
    fun findByRoleName(name: RoleEnum) : RoleEntity?
}