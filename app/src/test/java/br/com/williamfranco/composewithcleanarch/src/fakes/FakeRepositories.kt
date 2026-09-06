package br.com.williamfranco.composewithcleanarch.src.fakes

import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.entities.SettingEntity
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories.SettingRepository
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases.UpdateThemeUseCase
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.repositories.UserRepository
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.use_cases.GetAllUsersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeUserRepository(
    private val result: ResultPattern<List<UserEntity>, UserException>,
) : UserRepository {
    override suspend fun findAllUsers(): ResultPattern<List<UserEntity>, UserException> = result
}

class FakeGetAllUsersUseCase(
    private val result: ResultPattern<List<UserEntity>, UserException>,
) : GetAllUsersUseCase {
    override suspend fun invoke(): ResultPattern<List<UserEntity>, UserException> = result
}

class FakeSettingRepository(
    initialTheme: SettingEntity = SettingEntity(),
) : SettingRepository {

    private val themeState = MutableStateFlow(initialTheme)
    override val theme: Flow<SettingEntity> = themeState.asStateFlow()

    override suspend fun readTheme(): SettingEntity = themeState.value

    override suspend fun updateTheme(isDarkTheme: Boolean) {
        themeState.value = SettingEntity(isDarkTheme = isDarkTheme)
    }
}

class FakeUpdateThemeUseCase(
    private val settingRepository: SettingRepository,
) : UpdateThemeUseCase {
    override suspend fun invoke(isDarkTheme: Boolean) {
        settingRepository.updateTheme(isDarkTheme = isDarkTheme)
    }
}
