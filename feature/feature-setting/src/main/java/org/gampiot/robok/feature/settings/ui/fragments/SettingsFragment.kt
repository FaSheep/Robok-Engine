package org.gampiot.robok.feature.settings.ui.fragments

import android.os.Bundle

import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.annotation.IdRes 
import androidx.fragment.app.Fragment

import com.google.android.material.transition.MaterialSharedAxis

import org.gampiot.robok.feature.settings.R
import org.gampiot.robok.feature.settings.ui.fragments.editor.SettingsEditorFragment
import org.gampiot.robok.feature.settings.ui.fragments.about.AboutFragment
import org.gampiot.robok.feature.res.Strings
import org.gampiot.robok.feature.util.base.preference.BaseSettingFragment
import org.gampiot.robok.feature.util.base.preference.BasePreferenceFragment 
import org.gampiot.robok.feature.component.terminal.RobokTerminal

class SettingsFragment(
     private val transitionAxis: Int = MaterialSharedAxis.X,
     @IdRes private val fragmentLayoutResIdBSF: Int
): BaseSettingFragment(
       MaterialSharedAxis.X, 
       Strings.settings_about_title, 
       { SettingsTopFragment(transitionAxis, fragmentLayoutResIdBSF) },
       fragmentLayoutResIdBSF
   )

class SettingsTopFragment(
     private val transitionAxis: Int = MaterialSharedAxis.X, 
     @IdRes private val fragmentLayoutResIdBPF: Int
) : BasePreferenceFragment(fragmentLayoutResIdBPF) {

    private lateinit var terminal: RobokTerminal
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(transitionAxis, true)
        returnTransition = MaterialSharedAxis(transitionAxis, false)
        exitTransition = MaterialSharedAxis(transitionAxis, true)
        reenterTransition = MaterialSharedAxis(transitionAxis, false)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_top, rootKey)
        terminal = RobokTerminal(requireContext())
        terminal.addLog("fragmentLayoutResId: ${fragmentLayoutResIdBPF}")
        terminal.show()
    }
    
    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "settings_editor" -> {
                openFragment(SettingsEditorFragment(MaterialSharedAxis.X, fragmentLayoutResIdBPF))
                return true
            }
            "settings_about" -> {
                openFragment(AboutFragment(MaterialSharedAxis.X, fragmentLayoutResIdBPF))
                return true
            }
        }
        return super.onPreferenceTreeClick(preference)
    }
}
