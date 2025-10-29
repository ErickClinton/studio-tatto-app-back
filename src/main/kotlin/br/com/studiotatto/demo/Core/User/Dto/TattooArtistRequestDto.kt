package br.com.studiotatto.demo.Core.User.Dto

import java.util.UUID

data class TattooArtistRequestDto(
    val artisticName: String,
    val bio: String?,
    val styleIds: List<UUID>,
    val experienceYears: Int,
    val studioId: UUID,
    val availabilityId : UUID,
)
