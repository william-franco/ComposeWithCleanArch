package br.com.williamfranco.composewithcleanarch.src.features.users.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.williamfranco.composewithcleanarch.src.common.patterns.StatePattern
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.use_cases.GetAllUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

typealias UsersState = StatePattern<List<UserEntity>, UserException>

interface UserViewModel {
    val state: StateFlow<UsersState>
    fun getAllUsers()
    fun findUserById(userId: Int): UserEntity?
}

class UserViewModelImpl(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel(), UserViewModel {

    private val _state = MutableStateFlow<UsersState>(StatePattern.Initial)
    override val state: StateFlow<UsersState> = _state.asStateFlow()

    private var cachedUsers: List<UserEntity> = emptyList()

    init {
        getAllUsers()
    }

    override fun getAllUsers() {
        viewModelScope.launch {
            _state.value = StatePattern.Loading

            val nextState = getAllUsersUseCase().fold(
                onSuccess = { users ->
                    cachedUsers = users
                    StatePattern.Success(users)
                },
                onError = { error -> StatePattern.Error(error) },
            )

            _state.value = nextState
        }
    }

    override fun findUserById(userId: Int): UserEntity? =
        cachedUsers.firstOrNull { it.id == userId }
}
