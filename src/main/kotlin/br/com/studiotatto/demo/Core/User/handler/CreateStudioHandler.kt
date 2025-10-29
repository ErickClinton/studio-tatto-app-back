package br.com.studiotatto.demo.Core.User.handler

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import br.com.studiotatto.demo.Core.User.Dto.AddressRequestDto
import br.com.studiotatto.demo.Core.User.Dto.StudioRequestDto
import br.com.studiotatto.demo.Core.User.UserEntity
import br.com.studiotatto.demo.Module.Address.AddressService
import br.com.studiotatto.demo.Module.Studio.StudioEntity
import br.com.studiotatto.demo.Module.Studio.StudioService

class CreateStudioHandler(
    private val studioService: StudioService,
    private val addressService: AddressService
) : ICreateUserHandler<StudioRequestDto> {

    override val supportedRole = RoleEnum.STUDIO

    override fun handle(user: UserEntity, payload: StudioRequestDto): StudioEntity {
        val address = addressService.save(
            AddressRequestDto(
                logradouro = payload.address.logradouro,
                cep = payload.address.cep,
                numero = payload.address.numero,
                complemento = payload.address.complemento
            )
        )

        val entity = StudioEntity(
            user = user,
            cnpj = payload.cnpj,
            address = address
        )

        return studioService.save(entity)
    }
}