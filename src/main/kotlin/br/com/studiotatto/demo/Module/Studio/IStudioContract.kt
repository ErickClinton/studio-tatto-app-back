package br.com.studiotatto.demo.Module.Studio

import java.util.*

interface IStudioContract {
    fun save(studioEntity: StudioEntity) : StudioEntity
    fun findById(id: UUID): StudioEntity?
}