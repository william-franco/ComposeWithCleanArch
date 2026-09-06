package br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases

import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories.SettingRepository

interface UpdateThemeUseCase {
    suspend operator fun invoke(isDarkTheme: Boolean)
}

class UpdateThemeUseCaseImpl(
    private val settingRepository: SettingRepository,
) : UpdateThemeUseCase {

    override suspend fun invoke(isDarkTheme: Boolean) {
        try {
            settingRepository.updateTheme(isDarkTheme = isDarkTheme)
        } catch (error: Exception) {
            throw Exception("UpdateThemeUseCase: $error")
        }
    }
}
