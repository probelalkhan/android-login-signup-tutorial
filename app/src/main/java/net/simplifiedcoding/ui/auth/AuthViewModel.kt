package net.simplifiedcoding.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.AuthRepository
import net.simplifiedcoding.data.responses.LoginResponse
import net.simplifiedcoding.ui.base.BaseViewModel

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }
}