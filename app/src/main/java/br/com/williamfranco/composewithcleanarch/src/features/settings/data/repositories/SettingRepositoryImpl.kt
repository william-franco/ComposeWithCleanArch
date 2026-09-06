package br.com.williamfranco.composewithcleanarch.src.features.settings.data.repositories

import br.com.williamfranco.composewithcleanarch.src.features.settings.data.data_sources.SettingDataSource
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories.SettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingRepositoryImpl(
    private val settingDataSource: SettingDataSource,
) : SettingRepository {

    override val theme: Flow<SettingEntity> = settingDataSource.theme.map { it.toEntity() }

    override suspend fun readTheme(): SettingEntity = settingDataSource.readTheme().toEntity()

    override suspend fun updateTheme(isDarkTheme: Boolean) {
        settingDataSource.updateTheme(isDarkTheme = isDarkTheme)
    }
}
