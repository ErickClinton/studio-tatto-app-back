package br.com.studiotatto.demo.Core.User.handler

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import br.com.studiotatto.demo.Core.User.Dto.TattooArtistRequestDto
import br.com.studiotatto.demo.Core.User.UserEntity
import br.com.studiotatto.demo.Module.Studio.StudioService
import br.com.studiotatto.demo.Module.TattoArtist.TattoArtistService
import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import br.com.studiotatto.demo.Shared.AvailabilityStatus.AvailabilityStatusService
import br.com.studiotatto.demo.Shared.StyleTatto.StyleTattoService
import org.springframework.stereotype.Component

@Component
class CreateTattooArtistHandler(
    private val tattooArtistService: TattoArtistService,
    private val styleTattoService: StyleTattoService,
    private val studioService: StudioService,
    private val availabilityService: AvailabilityStatusService
) : ICreateUserHandler<TattooArtistRequestDto> {

    override val supportedRole = RoleEnum.EMPLOYEE

    override fun handle(user: UserEntity, payload: TattooArtistRequestDto): TattooArtistEntity {
        val studio = studioService.findById(payload.studioId)
            ?: throw IllegalArgumentException("Estúdio ${payload.studioId} não encontrado")

        val styles = styleTattoService.findByIds(payload.styleIds)
            .toMutableSet()
            .ifEmpty { throw IllegalArgumentException("Nenhum estilo válido encontrado.") }

        val availability = availabilityService.findById(payload.availabilityId)
            ?: throw IllegalArgumentException("Disponibilidade ${payload.availabilityId} não encontrada")

        val artist = TattooArtistEntity.from(payload, user, studio, styles, availability)
        return tattooArtistService.save(artist)
    }
}