package br.com.studiotatto.demo.Module.Address

import br.com.studiotatto.demo.Core.User.Dto.AddressRequestDto

interface IAddressContract {
    fun save(AddressDto: AddressRequestDto): AddressEntity
}