package br.com.williamfranco.composewithcleanarch.src.features.users.presentation.view_models

import br.com.williamfranco.composewithcleanarch.src.common.patterns.StatePattern
import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.fakes.FakeGetAllUsersUseCase
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loads users successfully`() = runTest {
        val users = listOf(
            UserEntity(id = 1, name = "Leanne Graham", email = "leanne@example.com"),
        )
        val viewModel = UserViewModelImpl(
            FakeGetAllUsersUseCase(ResultPattern.Success(users)),
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is StatePattern.Success)
        assertEquals(users, (state as StatePattern.Success).data)
    }

    @Test
    fun `emits error when use case fails`() = runTest {
        val error = UserException("Device not connected.")
        val viewModel = UserViewModelImpl(
            FakeGetAllUsersUseCase(ResultPattern.Error(error)),
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is StatePattern.Error)
        assertEquals(error.message, (state as StatePattern.Error).error.message)
    }

    @Test
    fun `findUserById returns cached user`() = runTest {
        val users = listOf(
            UserEntity(id = 1, name = "Leanne Graham", email = "leanne@example.com"),
            UserEntity(id = 2, name = "Ervin Howell", email = "ervin@example.com"),
        )
        val viewModel = UserViewModelImpl(
            FakeGetAllUsersUseCase(ResultPattern.Success(users)),
        )

        advanceUntilIdle()

        assertEquals(users.first(), viewModel.findUserById(1))
    }
}
