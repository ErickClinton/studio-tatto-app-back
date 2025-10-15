package br.com.studiotatto.demo.Module.TattooGallery

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tattoo_images")
class TattooImageEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id", nullable = false)
    var gallery: TattooGalleryEntity,

    @Column(name = "file_name", nullable = false)
    var fileName: String = "",

    @Column(name = "file_url", nullable = false)
    var fileUrl: String = "",

    @Column(name = "content_type", nullable = false)
    var contentType: String = "",

    @Column(name = "file_size_bytes", nullable = false)
    var fileSizeBytes: Long = 0L,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now()
) {
    constructor() : this(
        id = null,
        gallery = TattooGalleryEntity(),
        fileName = "",
        fileUrl = "",
        contentType = "",
        fileSizeBytes = 0L
    )
}
