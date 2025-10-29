package br.com.studiotatto.demo.Core.User.Dto

data class StudioRequestDto(
    val cnpj: String,
    val address: AddressRequestDto
)