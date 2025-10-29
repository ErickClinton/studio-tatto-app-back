package br.com.studiotatto.demo.Core.User

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID


interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email:String): UserEntity?
}