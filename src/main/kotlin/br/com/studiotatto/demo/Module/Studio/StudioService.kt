package br.com.studiotatto.demo.Module.Studio

import org.springframework.stereotype.Service
import java.util.*

@Service
class StudioService(private val studioRepository: StudioRepository): IStudioContract {
    override fun save(studioEntity: StudioEntity): StudioEntity {
        return studioRepository.save(studioEntity)
    }

    override fun findById(id: UUID): StudioEntity?{
        return studioRepository.findById(id).orElse(null);
    }
}