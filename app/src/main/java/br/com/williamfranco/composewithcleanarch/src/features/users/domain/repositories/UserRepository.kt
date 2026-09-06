package br.com.williamfranco.composewithcleanarch.src.features.users.domain.repositories

import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity

interface UserRepository {
    suspend fun findAllUsers(): ResultPattern<List<UserEntity>, UserException>
}
