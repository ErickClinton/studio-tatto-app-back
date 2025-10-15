package br.com.studiotatto.demo.Module.Appointment

import br.com.studiotatto.demo.Module.Client.ClientEntity
import br.com.studiotatto.demo.Module.Studio.StudioEntity
import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import br.com.studiotatto.demo.Shared.Enum.AppointmentStatusEnum
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "appointments")
class AppointmentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tattoo_artist_id", nullable = false)
    var tattooArtist: TattooArtistEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = false)
    var studio: StudioEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    var client: ClientEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: AppointmentStatusEnum = AppointmentStatusEnum.PENDING,

    @Column(name = "price", nullable = true)
    var price: Float? = null,

    @Column(name = "start_datetime", nullable = false)
    var startDateTime: Instant,

    @Column(name = "end_datetime", nullable = false)
    var endDateTime: Instant,

    @Column(name = "is_available", nullable = false)
    var isAvailable: Boolean = true,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {
    constructor() : this(
        id = null,
        tattooArtist = TattooArtistEntity(),
        studio = StudioEntity(),
        client = ClientEntity(),
        status = AppointmentStatusEnum.PENDING,
        price = null,
        startDateTime = Instant.now(),
        endDateTime = Instant.now(),
        isAvailable = true
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
