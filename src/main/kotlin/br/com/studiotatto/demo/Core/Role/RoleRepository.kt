package br.com.studiotatto.demo.Core.Role

import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<RoleEntity, Long> {
}