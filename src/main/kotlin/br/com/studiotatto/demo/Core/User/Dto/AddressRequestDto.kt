package br.com.studiotatto.demo.Core.User.Dto

data class AddressRequestDto(
    val cep: String,
    val logradouro: String,
    val numero: String,
    val complemento: String?
)