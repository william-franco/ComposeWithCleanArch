package br.com.williamfranco.composewithcleanarch.src.features.users.domain.use_cases

import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.fakes.FakeUserRepository
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAllUsersUseCaseTest {

    @Test
    fun `returns users from repository`() = runTest {
        val users = listOf(
            UserEntity(id = 1, name = "Leanne Graham", email = "leanne@example.com"),
        )
        val useCase = GetAllUsersUseCaseImpl(
            FakeUserRepository(ResultPattern.Success(users)),
        )

        val result = useCase()

        assertTrue(result is ResultPattern.Success)
        assertEquals(users, (result as ResultPattern.Success).value)
    }

    @Test
    fun `returns error from repository`() = runTest {
        val error = UserException("Device not connected.")
        val useCase = GetAllUsersUseCaseImpl(
            FakeUserRepository(ResultPattern.Error(error)),
        )

        val result = useCase()

        assertTrue(result is ResultPattern.Error)
        assertEquals(error.message, (result as ResultPattern.Error).error.message)
    }
}
