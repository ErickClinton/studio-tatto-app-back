package br.com.studiotatto.demo.Module.TattooGallery

import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tattoo_gallery")
class TattooGalleryEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne(mappedBy = "tattooGallery", fetch = FetchType.LAZY)
    var artist: TattooArtistEntity? = null,

    @OneToMany(mappedBy = "gallery", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var images: MutableList<TattooImageEntity> = mutableListOf(),

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {
    constructor() : this(
        id = null,
        artist = null,
        images = mutableListOf()
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
