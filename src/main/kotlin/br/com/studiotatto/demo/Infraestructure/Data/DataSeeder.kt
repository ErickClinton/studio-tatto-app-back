package br.com.studiotatto.demo.config

import br.com.studiotatto.demo.Core.Enum.RoleEnum
import br.com.studiotatto.demo.Core.Role.RoleEntity
import br.com.studiotatto.demo.Core.Role.RoleRepository
import br.com.studiotatto.demo.Core.Style.StyleTattoEntity
import br.com.studiotatto.demo.Shared.AvailabilityStatus.AvailabilityStatusEntity
import br.com.studiotatto.demo.Shared.AvailabilityStatus.AvailabilityStatusRepository
import br.com.studiotatto.demo.Shared.StyleTatto.StyleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class DataSeeder(
    private val styleRepository: StyleRepository,
    private val availabilityStatusRepository: AvailabilityStatusRepository,
    private val roleRepository: RoleRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        seedStyles()
        seedAvailabilityStatus()
        seedRoles()
    }

    private fun seedStyles() {
        if (styleRepository.count() == 0L) {
            val styles = listOf(
                StyleTattoEntity(name = "BLACKWORK", description = "Tatuagem preta sólida"),
                StyleTattoEntity(name = "REALISM", description = "Tatuagem realista e detalhada"),
                StyleTattoEntity(name = "TRIBAL", description = "Tatuagem tribal tradicional"),
                StyleTattoEntity(name = "JAPANESE", description = "Tatuagem japonesa colorida"),
                StyleTattoEntity(name = "MINIMALIST", description = "Tatuagem minimalista e pequena")
            )
            styleRepository.saveAll(styles)
            println("✅ Estilos de tatuagem inseridos com sucesso!")
        } else {
            println("ℹ️ Tabela 'styles' já contém registros, seed ignorado.")
        }
    }

    private fun seedAvailabilityStatus() {
        if (availabilityStatusRepository.count() == 0L) {
            val statuses = listOf(
                AvailabilityStatusEntity(
                    status = "AVAILABLE",
                    description = "Disponível para novos agendamentos",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now()
                ),
                AvailabilityStatusEntity(
                    status = "BUSY",
                    description = "Ocupado no momento",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now()
                ),
                AvailabilityStatusEntity(
                    status = "ON_VACATION",
                    description = "Em período de férias",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now()
                ),
                AvailabilityStatusEntity(
                    status = "UNAVAILABLE",
                    description = "Indisponível temporariamente",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now()
                )
            )
            availabilityStatusRepository.saveAll(statuses)
            println("✅ Status de disponibilidade inseridos com sucesso!")
        } else {
            println("ℹ️ Tabela 'availability_status' já contém registros, seed ignorado.")
        }
    }

    private fun seedRoles() {
        if (roleRepository.count() == 0L) {
            val roles = listOf(
                RoleEntity(roleName = RoleEnum.USER),
                RoleEntity(roleName = RoleEnum.EMPLOYEE),
                RoleEntity(roleName = RoleEnum.STUDIO)
            )
            roleRepository.saveAll(roles)
            println("✅ Roles inseridas com sucesso!")
        } else {
            println("ℹ️ Tabela 'roles' já contém registros, seed ignorado.")
        }
    }
}
