package br.com.studiotatto.demo.Module.TattoArtist

import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import org.springframework.stereotype.Service

@Service
class TattoArtistService(private val tattoArtistRepository: TatttoArtistRepository) : ITattoArtistContract {

    override fun save(tattoArtistEntity: TattooArtistEntity): TattooArtistEntity{
        return tattoArtistRepository.save(tattoArtistEntity)
    }
}