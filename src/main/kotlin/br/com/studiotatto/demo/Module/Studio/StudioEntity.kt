package br.com.studiotatto.demo.Module.Studio

import br.com.studiotatto.demo.Core.User.UserEntity
import br.com.studiotatto.demo.Module.Address.AddressEntity
import br.com.studiotatto.demo.Module.TattooArtist.TattooArtistEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "studios")
class StudioEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Column(name = "cnpj", nullable = false, unique = true, length = 18)
    var cnpj: String = "",

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", nullable = false)
    var address: AddressEntity,

    @OneToMany(mappedBy = "studio", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var tattooArtists: MutableList<TattooArtistEntity> = mutableListOf(),

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {

    constructor() : this(
        null,
        UserEntity(),
        "",
        AddressEntity(),
        mutableListOf()
    )

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }
}
