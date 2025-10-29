package br.com.studiotatto.demo.Module.Client

import org.springframework.stereotype.Service

@Service
class ClientService(private val clientRepository: ClientRepository): IClientContract {

    override fun save(clientEntity: ClientEntity): ClientEntity {
        return clientRepository.save(clientEntity);
    }
}