package br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.view_models

import br.com.williamfranco.composewithcleanarch.src.fakes.FakeSettingRepository
import br.com.williamfranco.composewithcleanarch.src.fakes.FakeUpdateThemeUseCase
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingViewModelTest {

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
    fun `observes initial theme from repository`() = runTest {
        val repository = FakeSettingRepository(SettingEntity(isDarkTheme = false))
        val viewModel = SettingViewModelImpl(
            settingRepository = repository,
            updateThemeUseCase = FakeUpdateThemeUseCase(repository),
        )

        advanceUntilIdle()

        assertFalse(viewModel.state.value.isDarkTheme)
    }

    @Test
    fun `updates theme when changed`() = runTest {
        val repository = FakeSettingRepository()
        val viewModel = SettingViewModelImpl(
            settingRepository = repository,
            updateThemeUseCase = FakeUpdateThemeUseCase(repository),
        )

        advanceUntilIdle()
        viewModel.changeTheme(isDarkTheme = true)
        advanceUntilIdle()

        assertTrue(viewModel.state.value.isDarkTheme)
    }
}
