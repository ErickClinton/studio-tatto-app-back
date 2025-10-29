package br.com.studiotatto.demo.Shared.StyleTatto

import br.com.studiotatto.demo.Core.Style.StyleTattoEntity
import java.util.*

interface IStyleTattoContract {
    fun findByIds(ids: List<UUID>): List<StyleTattoEntity>
}