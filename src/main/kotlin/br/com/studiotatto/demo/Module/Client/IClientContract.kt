package br.com.studiotatto.demo.Module.Client

interface IClientContract {
    fun save(clientDto: ClientEntity): ClientEntity
}