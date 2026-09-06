package br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories.SettingRepository
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases.UpdateThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface SettingViewModel {
    val state: StateFlow<SettingEntity>
    fun changeTheme(isDarkTheme: Boolean)
}

class SettingViewModelImpl(
    private val settingRepository: SettingRepository,
    private val updateThemeUseCase: UpdateThemeUseCase,
) : ViewModel(), SettingViewModel {

    private val _state = MutableStateFlow(SettingEntity())
    override val state: StateFlow<SettingEntity> = _state.asStateFlow()

    init {
        observeTheme()
    }

    private fun observeTheme() {
        settingRepository.theme
            .onEach { entity -> _state.update { entity } }
            .launchIn(viewModelScope)
    }

    override fun changeTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            updateThemeUseCase(isDarkTheme = isDarkTheme)
        }
    }
}
