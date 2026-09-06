package br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases

import br.com.williamfranco.composewithcleanarch.src.fakes.FakeSettingRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class UpdateThemeUseCaseTest {

    @Test
    fun `updates theme in repository`() = runTest {
        val repository = FakeSettingRepository()
        val useCase = UpdateThemeUseCaseImpl(repository)

        useCase(isDarkTheme = true)

        assertTrue(repository.readTheme().isDarkTheme)
    }
}
