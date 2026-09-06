package br.com.williamfranco.composewithcleanarch.src.features.users.data.data_sources

import br.com.williamfranco.composewithcleanarch.src.common.constants.ApiConstant
import br.com.williamfranco.composewithcleanarch.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithcleanarch.src.common.services.ConnectionService
import br.com.williamfranco.composewithcleanarch.src.common.services.HttpService
import br.com.williamfranco.composewithcleanarch.src.features.users.data.exceptions.UserException
import br.com.williamfranco.composewithcleanarch.src.features.users.data.models.UserModel

interface UserDataSource {
    suspend fun findAllUsers(): ResultPattern<List<UserModel>, UserException>
}

class UserDataSourceImpl(
    private val connectionService: ConnectionService,
    private val httpService: HttpService,
) : UserDataSource {

    override suspend fun findAllUsers(): ResultPattern<List<UserModel>, UserException> {
        return try {
            connectionService.checkConnection()

            if (!connectionService.isConnected) {
                return ResultPattern.Error(UserException("Device not connected."))
            }

            val users: List<UserModel> = httpService.getData(ApiConstant.USERS)
            ResultPattern.Success(users)
        } catch (error: Exception) {
            ResultPattern.Error(UserException("Unexpected error: $error"))
        }
    }
}
