package br.com.studiotatto.demo.Module.TattooRequest

import br.com.studiotatto.demo.Module.Appointment.AppointmentEntity
import br.com.studiotatto.demo.Module.Client.ClientEntity
import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import br.com.studiotatto.demo.Module.TattooGallery.TattooImageEntity
import br.com.studiotatto.demo.Shared.Enum.ColorPreferenceEnum
import br.com.studiotatto.demo.Shared.Enum.RequestStatusEnum
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tattoo_requests")
class TattooRequestEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    var appointment: AppointmentEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    var client: ClientEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tattoo_artist_id", nullable = false)
    var tattooArtist: TattooArtistEntity,

    @Column(name = "body_location", length = 100, nullable = false)
    var bodyLocation: String = "",

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tattoo_request_reference_images",
        joinColumns = [JoinColumn(name = "tattoo_request_id")],
        inverseJoinColumns = [JoinColumn(name = "image_id")]
    )
    var referenceImages: MutableSet<TattooImageEntity> = mutableSetOf(),

    @Column(name = "size_estimate", length = 50)
    var sizeEstimate: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "color_preference", nullable = false)
    var colorPreference: ColorPreferenceEnum = ColorPreferenceEnum.BLACK_GREY,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: RequestStatusEnum = RequestStatusEnum.PENDING,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {

    constructor() : this(
        id = null,
        appointment = AppointmentEntity(),
        client = ClientEntity(),
        tattooArtist = TattooArtistEntity(),
        bodyLocation = "",
        description = "",
        referenceImages = mutableSetOf(),
        sizeEstimate = null,
        colorPreference = ColorPreferenceEnum.BLACK_GREY,
        status = RequestStatusEnum.PENDING
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
