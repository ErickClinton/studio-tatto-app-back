package br.com.studiotatto.demo.Shared.StyleTatto

import br.com.studiotatto.demo.Core.Style.StyleTattoEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class StyleTattoService(private val styleRepository: StyleRepository) : IStyleTattoContract {
    override fun findByIds(ids: List<UUID>): List<StyleTattoEntity> {
        return styleRepository.findAllByIdIn(ids)
    }
}