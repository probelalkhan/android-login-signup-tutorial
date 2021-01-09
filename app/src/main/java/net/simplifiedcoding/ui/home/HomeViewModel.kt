package net.simplifiedcoding.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.UserRepository
import net.simplifiedcoding.data.responses.LoginResponse
import net.simplifiedcoding.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}