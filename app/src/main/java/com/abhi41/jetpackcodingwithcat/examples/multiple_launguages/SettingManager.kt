package com.abhi41.jetpackcodingwithcat.examples.multiple_launguages

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings_preferences"
)

class SettingManager(private val ctx: Context) {
    companion object {
        private val SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
        private val SELECTED_LANGUAGE_CODE = stringPreferencesKey("selected_language_code")
    }

    suspend fun saveSelectedLanguage(appLanguage: AppLanguage) {
        ctx.applicationContext.dataStore.edit { prefrences ->
            prefrences[SELECTED_LANGUAGE] = appLanguage.selectedLang
            prefrences[SELECTED_LANGUAGE_CODE] = appLanguage.selectedLangCode
        }
    }

    private val languageFlow: Flow<AppLanguage> =
        ctx.applicationContext.dataStore.data.map { pref ->
            AppLanguage(
                pref[SELECTED_LANGUAGE] ?: "English",
                pref[SELECTED_LANGUAGE_CODE] ?: "en",
            )
        }

    val currentLang: AppLanguage
        get() = runBlocking { languageFlow.first() }

     fun observerLanguageChanges(): Flow<AppLanguage> {
         return languageFlow
     }
}

data class AppLanguage(val selectedLang: String, val selectedLangCode: String)