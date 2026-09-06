package br.com.williamfranco.composewithcleanarch.src.features.users.domain.use_cases

import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.repositories.UserRepository

interface GetAllUsersUseCase {
    suspend operator fun invoke(): ResultPattern<List<UserEntity>, UserException>
}

class GetAllUsersUseCaseImpl(
    private val userRepository: UserRepository,
) : GetAllUsersUseCase {

    override suspend fun invoke(): ResultPattern<List<UserEntity>, UserException> {
        return try {
            userRepository.findAllUsers()
        } catch (error: Exception) {
            throw Exception("GetAllUsersUseCase: $error")
        }
    }
}
