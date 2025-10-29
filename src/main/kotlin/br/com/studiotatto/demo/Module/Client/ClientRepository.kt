package br.com.studiotatto.demo.Module.Client

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository : JpaRepository<ClientEntity, UUID> {
}