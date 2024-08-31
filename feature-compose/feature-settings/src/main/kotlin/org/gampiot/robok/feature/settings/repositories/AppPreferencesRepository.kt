package org.gampiot.robok.feature.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map

private val editorThemePreference = intPreferencesKey("is_use_monet")

class AppPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
     val editorTheme = dataStore.data
          .map {
              it[isUseMonetPreference] ?: 0
          }
        
     suspend fun changeEditorTheme(value: String) {
         dataStore.edit { preferences ->
             preferences[editorThemePreference] = value
         }
     }
}
