package br.com.studiotatto.demo.Module.Address

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AddressRepository : JpaRepository<AddressEntity, UUID> {
}