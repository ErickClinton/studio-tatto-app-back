import br.com.studiotatto.demo.Core.User.Dto.ClientRequestDto
import br.com.studiotatto.demo.Core.User.Dto.StudioRequestDto
import br.com.studiotatto.demo.Core.User.Dto.TattooArtistRequestDto

sealed class CreateUserRequestBaseDto {
    abstract val name: String
    abstract val email: String
    abstract val password: String
    abstract val phone: String
}

data class CreateClientRequestDto(
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    val client: ClientRequestDto
) : CreateUserRequestBaseDto()

data class CreateTattooArtistUserRequestDto(
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    val tattooArtist: TattooArtistRequestDto
) : CreateUserRequestBaseDto()

data class CreateStudioUserRequestDto(
    override val name: String,
    override val email: String,
    override val password: String,
    override val phone: String,
    val studio: StudioRequestDto
) : CreateUserRequestBaseDto()
