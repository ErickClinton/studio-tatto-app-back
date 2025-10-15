package br.com.studiotatto.demo.Shared.StyleTatto

import br.com.studiotatto.demo.Core.Style.StyleTattoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StyleRepository : JpaRepository<StyleTattoEntity, UUID>