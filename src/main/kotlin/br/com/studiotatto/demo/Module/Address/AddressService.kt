package br.com.studiotatto.demo.Module.Address

import br.com.studiotatto.demo.Core.User.Dto.AddressRequestDto
import org.springframework.stereotype.Service

@Service
class AddressService(private val addressRepository: AddressRepository) : IAddressContract {

    override fun save(AddressDto: AddressRequestDto): AddressEntity {
        return addressRepository.save(AddressEntity(
            cep = AddressDto.cep,
            numero = AddressDto.numero,
            logradouro = AddressDto.logradouro,
            complemento = AddressDto.complemento,
        ))
    }


}