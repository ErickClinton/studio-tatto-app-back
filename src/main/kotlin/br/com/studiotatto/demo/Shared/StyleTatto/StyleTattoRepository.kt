package br.com.studiotatto.demo.Shared.StyleTatto

import br.com.studiotatto.demo.Core.Style.StyleTattoEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface StyleRepository : JpaRepository<StyleTattoEntity, UUID>{
    fun findAllByIdIn(ids: List<UUID>): List<StyleTattoEntity>
}