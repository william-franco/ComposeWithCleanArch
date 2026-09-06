package br.com.williamfranco.composewithcleanarch.src.di

import br.com.williamfranco.composewithcleanarch.src.common.services.ConnectionService
import br.com.williamfranco.composewithcleanarch.src.common.services.ConnectionServiceImpl
import br.com.williamfranco.composewithcleanarch.src.common.services.HttpService
import br.com.williamfranco.composewithcleanarch.src.features.settings.data.data_sources.SettingDataSource
import br.com.williamfranco.composewithcleanarch.src.features.settings.data.data_sources.SettingDataSourceImpl
import br.com.williamfranco.composewithcleanarch.src.features.settings.data.data_sources.settingsDataStore
import br.com.williamfranco.composewithcleanarch.src.features.settings.data.repositories.SettingRepositoryImpl
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.repositories.SettingRepository
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases.ReadThemeUseCase
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases.ReadThemeUseCaseImpl
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases.UpdateThemeUseCase
import br.com.williamfranco.composewithcleanarch.src.features.settings.domain.use_cases.UpdateThemeUseCaseImpl
import br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.view_models.SettingViewModel
import br.com.williamfranco.composewithcleanarch.src.features.settings.presentation.view_models.SettingViewModelImpl
import br.com.williamfranco.composewithcleanarch.src.features.users.data.data_sources.UserDataSource
import br.com.williamfranco.composewithcleanarch.src.features.users.data.data_sources.UserDataSourceImpl
import br.com.williamfranco.composewithcleanarch.src.features.users.data.repositories.UserRepositoryImpl
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.repositories.UserRepository
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.use_cases.GetAllUsersUseCase
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.use_cases.GetAllUsersUseCaseImpl
import br.com.williamfranco.composewithcleanarch.src.features.users.presentation.view_models.UserViewModel
import br.com.williamfranco.composewithcleanarch.src.features.users.presentation.view_models.UserViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HttpService() }
    single<ConnectionService> { ConnectionServiceImpl(get()) }

    single<UserDataSource> { UserDataSourceImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<GetAllUsersUseCase> { GetAllUsersUseCaseImpl(get()) }
    viewModelOf(::UserViewModelImpl) { bind<UserViewModel>() }

    single { androidContext().settingsDataStore }
    single<SettingDataSource> { SettingDataSourceImpl(get()) }
    single<SettingRepository> { SettingRepositoryImpl(get()) }
    single<ReadThemeUseCase> { ReadThemeUseCaseImpl(get()) }
    single<UpdateThemeUseCase> { UpdateThemeUseCaseImpl(get()) }
    viewModelOf(::SettingViewModelImpl) { bind<SettingViewModel>() }
}
