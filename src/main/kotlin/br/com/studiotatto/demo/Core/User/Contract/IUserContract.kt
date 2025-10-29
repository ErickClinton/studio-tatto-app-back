package br.com.studiotatto.demo.Core.User.Contract

import CreateUserRequestBaseDto

interface IUserContract {
    fun createUser(userRequestDto: CreateUserRequestBaseDto) : Any
}