package br.com.studiotatto.demo.Core.User.handler

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import br.com.studiotatto.demo.Core.User.UserEntity

interface ICreateUserHandler<T> {
    val supportedRole: RoleEnum
    fun handle(user: UserEntity, payload: T): Any
}