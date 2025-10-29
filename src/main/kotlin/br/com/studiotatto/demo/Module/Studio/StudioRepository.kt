package br.com.studiotatto.demo.Module.Studio

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface StudioRepository : JpaRepository<StudioEntity, UUID> {
}