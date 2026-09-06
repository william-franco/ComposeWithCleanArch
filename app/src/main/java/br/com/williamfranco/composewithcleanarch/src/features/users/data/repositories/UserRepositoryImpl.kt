package br.com.williamfranco.composewithcleanarch.src.features.users.data.repositories

import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.features.users.data.data_sources.UserDataSource
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.data.mappers.toEntities
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
) : UserRepository {

    override suspend fun findAllUsers(): ResultPattern<List<UserEntity>, UserException> {
        return try {
            when (val result = userDataSource.findAllUsers()) {
                is ResultPattern.Success -> ResultPattern.Success(result.value.toEntities())
                is ResultPattern.Error -> ResultPattern.Error(result.error)
            }
        } catch (error: Exception) {
            throw Exception("UserRepository: $error")
        }
    }
}
