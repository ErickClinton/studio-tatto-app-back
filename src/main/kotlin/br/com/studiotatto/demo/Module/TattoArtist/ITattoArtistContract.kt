package br.com.studiotatto.demo.Module.TattoArtist

import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity

interface ITattoArtistContract {
    fun save(tattoArtistEntity: TattooArtistEntity) : TattooArtistEntity
}