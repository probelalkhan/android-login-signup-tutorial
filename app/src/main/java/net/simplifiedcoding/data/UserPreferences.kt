package net.simplifiedcoding.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    val accessToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    val refreshToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val ACCESS_TOKEN = preferencesKey<String>("key_access_token")
        private val REFRESH_TOKEN = preferencesKey<String>("key_refresh_token")
    }

}