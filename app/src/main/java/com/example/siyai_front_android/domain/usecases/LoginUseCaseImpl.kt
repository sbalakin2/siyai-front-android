package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.LoginRepository
import com.example.siyai_front_android.presentation.auth.login.LoginState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String): LoginState {
        return when (val result = loginRepository.loginUser(email, password)) {
            is NetworkResult.Success -> LoginState.Success
            is NetworkResult.Error -> LoginState.Error(result.code, result.message)
            is NetworkResult.Exception -> LoginState.Exception(result.throwable.message.orEmpty())
        }
    }
}