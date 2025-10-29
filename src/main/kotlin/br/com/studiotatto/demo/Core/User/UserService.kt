package br.com.studiotatto.demo.Core.User

import CreateClientRequestDto
import CreateStudioUserRequestDto
import CreateTattooArtistUserRequestDto
import CreateUserRequestBaseDto
import br.com.studiotatto.demo.Core.Enum.RoleEnum
import br.com.studiotatto.demo.Core.Role.RoleService
import br.com.studiotatto.demo.Core.User.Contract.IUserContract
import br.com.studiotatto.demo.Core.User.Dto.*
import br.com.studiotatto.demo.Core.User.handler.ICreateUserHandler
import br.com.studiotatto.demo.exceptions.User.UserBadCredentialsException
import br.com.studiotatto.demo.exceptions.User.UserNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleService: RoleService,
    private val handlers: MutableList<out ICreateUserHandler<*>>,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtEncoder: JwtEncoder
): IUserContract {

    override fun createUser(userRequestDto: CreateUserRequestBaseDto): Any {

        val role = when (userRequestDto) {
            is CreateClientRequestDto -> RoleEnum.USER
            is CreateTattooArtistUserRequestDto -> RoleEnum.EMPLOYEE
            is CreateStudioUserRequestDto -> RoleEnum.STUDIO
            else -> {throw IllegalArgumentException("Role inválida")}
        }

        val user = userRepository.save(
            UserEntity(
                name = userRequestDto.name,
                email = userRequestDto.email,
                password = bCryptPasswordEncoder.encode(userRequestDto.password),
                phone = userRequestDto.phone,
                role = roleService.findRoleByName(role)
                    ?: throw IllegalArgumentException("Role inválida")
            )
        )

        val handler = handlers.first { it.supportedRole == role }

        @Suppress("UNCHECKED_CAST")
        return when (userRequestDto) {
            is CreateClientRequestDto -> (handler as ICreateUserHandler<ClientRequestDto>).handle(user, userRequestDto.client)
            is CreateTattooArtistUserRequestDto -> (handler as ICreateUserHandler<TattooArtistRequestDto>).handle(user, userRequestDto.tattooArtist)
            is CreateStudioUserRequestDto -> (handler as ICreateUserHandler<StudioRequestDto>).handle(user, userRequestDto.studio)
            else -> {}
        }
    }

     fun login(loginDto: LoginDto): LoginResponseDto {
        val user = this.userRepository.findByEmail(loginDto.email) ?:
        throw UserNotFoundException("User not found: ${loginDto.email}")

        user.validatePassword(loginDto.password,bCryptPasswordEncoder)

        val now = Instant.now()
        val expiresIn = 300L

        val claims = JwtClaimsSet.builder()
            .issuer("server")
            .subject(user.id.toString())
            .subject(user.role.toString())
            .expiresAt(now.plusSeconds(expiresIn))
            .issuedAt(now)
            .build()

        val jwtValue: String = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue()

        return LoginResponseDto(jwtValue, expiresIn)

    }

    fun UserEntity.validatePassword(password: String, bcrypt: BCryptPasswordEncoder) {
        when {
            !bcrypt.matches(password, this.password) -> {
                throw UserBadCredentialsException("Password or email incorrect")
            }
        }
    }

}