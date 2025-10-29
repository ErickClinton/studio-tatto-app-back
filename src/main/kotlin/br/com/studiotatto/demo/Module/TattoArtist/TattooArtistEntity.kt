package br.com.studiotatto.demo.Module.TattooArtist

import br.com.studiotatto.demo.Core.User.UserEntity
import br.com.studiotatto.demo.Shared.AvailabilityStatus.AvailabilityStatusEntity
import br.com.studiotatto.demo.Core.Style.StyleTattoEntity
import br.com.studiotatto.demo.Core.User.Dto.TattooArtistRequestDto
import br.com.studiotatto.demo.Module.Appointment.AppointmentEntity
import br.com.studiotatto.demo.Module.Studio.StudioEntity
import br.com.studiotatto.demo.Module.TattooGallery.TattooGalleryEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tattoo_artists")
class TattooArtistEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = false)
    var studio: StudioEntity,

    @Column(name = "artistic_name", nullable = false)
    var artisticName: String = "",

    @Column(name = "bio", columnDefinition = "TEXT")
    var bio: String = "",

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "artist_styles",
        joinColumns = [JoinColumn(name = "artist_id")],
        inverseJoinColumns = [JoinColumn(name = "style_id")]
    )
    var styles: MutableSet<StyleTattoEntity> = mutableSetOf(),

    @Column(name = "specialties")
    var specialties: String = "",

    @Column(name = "experience_years")
    var experienceYears: Int = 0,

    @Column(name = "rating")
    var rating: Double? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "availability_status_id", nullable = false)
    var availabilityStatus: AvailabilityStatusEntity,

    @Column(name = "profile_photo_url")
    var profilePhotoUrl: String? = null,

    @Column(name = "cover_photo_url")
    var coverPhotoUrl: String? = null,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "tattoo_gallery_id")
    var tattooGallery: TattooGalleryEntity? = null,

    @OneToMany(mappedBy = "tattooArtist", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var appointments: MutableList<AppointmentEntity> = mutableListOf(),

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {

    constructor() : this(
         null,
        UserEntity(),
        StudioEntity(),
        "",
        "",
        mutableSetOf(),
        "",
        0,
        null,
        AvailabilityStatusEntity(),
        null,
        null,
        null,
        mutableListOf()
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }

    companion object {
        fun from(dto: TattooArtistRequestDto, user: UserEntity, studio: StudioEntity, styles: MutableSet<StyleTattoEntity>, availability: AvailabilityStatusEntity): TattooArtistEntity {
            return TattooArtistEntity(
                user = user,
                studio = studio,
                artisticName = dto.artisticName,
                bio = dto.bio ?: "",
                styles = styles,
                experienceYears = dto.experienceYears,
                availabilityStatus = availability
            )
        }
    }
}
