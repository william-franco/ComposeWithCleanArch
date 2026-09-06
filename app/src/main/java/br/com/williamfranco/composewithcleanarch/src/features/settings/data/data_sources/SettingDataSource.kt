package br.com.williamfranco.composewithcleanarch.src.features.settings.data.data_sources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import br.com.williamfranco.composewithcleanarch.src.common.constants.ValueConstant
import br.com.williamfranco.composewithcleanarch.src.features.settings.data.models.SettingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "compose_with_clean_arch_settings",
)

interface SettingDataSource {
    val theme: Flow<SettingModel>
    suspend fun readTheme(): SettingModel
    suspend fun updateTheme(isDarkTheme: Boolean)
}

class SettingDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
) : SettingDataSource {

    override val theme: Flow<SettingModel> = dataStore.data.map { preferences ->
        SettingModel(
            isDarkTheme = preferences[DARK_MODE_KEY] ?: false,
        )
    }

    override suspend fun readTheme(): SettingModel = theme.first()

    override suspend fun updateTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkTheme
        }
    }

    private companion object {
        val DARK_MODE_KEY = booleanPreferencesKey(ValueConstant.DARK_MODE)
    }
}
