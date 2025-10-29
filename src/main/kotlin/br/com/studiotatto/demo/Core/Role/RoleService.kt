package br.com.studiotatto.demo.Core.Role

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository):IRoleService {

    override fun findRoleByName(name:RoleEnum): RoleEntity? {
        return roleRepository.findByRoleName(name);
    }
}