package br.com.studiotatto.demo.Core.User.Dto

data class ClientRequestDto(
    val birthDate: String,
    val preferences: String?,
    val address: AddressRequestDto
)