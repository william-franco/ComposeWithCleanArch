package br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories

import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    val theme: Flow<SettingEntity>
    suspend fun readTheme(): SettingEntity
    suspend fun updateTheme(isDarkTheme: Boolean)
}
