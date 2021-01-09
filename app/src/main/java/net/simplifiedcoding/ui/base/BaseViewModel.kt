package net.simplifiedcoding.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.simplifiedcoding.data.network.UserApi
import net.simplifiedcoding.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout() = withContext(Dispatchers.IO) { repository.logout() }

}