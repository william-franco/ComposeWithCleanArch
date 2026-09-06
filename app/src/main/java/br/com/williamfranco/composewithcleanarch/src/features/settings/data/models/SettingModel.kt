package br.com.williamfranco.composewithcleanarch.src.features.settings.data.models

import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity

data class SettingModel(
    val isDarkTheme: Boolean = false,
) {
    fun toEntity(): SettingEntity = SettingEntity(isDarkTheme = isDarkTheme)
}

fun SettingEntity.toModel(): SettingModel = SettingModel(isDarkTheme = isDarkTheme)
