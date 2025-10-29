package br.com.studiotatto.demo.Module.TattoArtist

import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TatttoArtistRepository : JpaRepository<TattooArtistEntity, UUID> {
}