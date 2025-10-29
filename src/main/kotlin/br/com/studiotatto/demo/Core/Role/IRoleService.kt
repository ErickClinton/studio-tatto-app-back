package br.com.studiotatto.demo.Core.Role

import br.com.studiotatto.demo.Core.Enum.RoleEnum

interface IRoleService {
    fun findRoleByName(name: RoleEnum): RoleEntity?
}