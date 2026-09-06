package br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases

import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories.SettingRepository

interface ReadThemeUseCase {
    suspend operator fun invoke(): SettingEntity
}

class ReadThemeUseCaseImpl(
    private val settingRepository: SettingRepository,
) : ReadThemeUseCase {

    override suspend fun invoke(): SettingEntity {
        return try {
            settingRepository.readTheme()
        } catch (error: Exception) {
            throw Exception("ReadThemeUseCase: $error")
        }
    }
}
