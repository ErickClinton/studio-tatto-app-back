package br.com.studiotatto.demo.Core.User.handler

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import br.com.studiotatto.demo.Core.User.Dto.AddressRequestDto
import br.com.studiotatto.demo.Core.User.Dto.ClientRequestDto
import br.com.studiotatto.demo.Core.User.UserEntity
import br.com.studiotatto.demo.Module.Address.AddressService
import br.com.studiotatto.demo.Module.Client.ClientEntity
import br.com.studiotatto.demo.Module.Client.ClientService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class CreateClientHandler(
    private val clientService: ClientService,
    private val addressService: AddressService
) : ICreateUserHandler<ClientRequestDto> {

    override val supportedRole = RoleEnum.USER

    override fun handle(user: UserEntity, payload: ClientRequestDto): ClientEntity {
        val address = addressService.save(
            AddressRequestDto(
                logradouro = payload.address.logradouro,
                cep = payload.address.cep,
                numero = payload.address.numero,
                complemento = payload.address.complemento
            )
        )

        val entity = ClientEntity(
            user = user,
            birthDate = LocalDate.parse(payload.birthDate),
            preferences = payload.preferences,
            address = address
        )

        return clientService.save(entity)
    }
}