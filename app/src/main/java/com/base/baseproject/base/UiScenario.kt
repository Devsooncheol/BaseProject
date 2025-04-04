package com.base.baseproject.base

import com.base.baseproject.ui.main.MainActivity
import com.base.baseproject.ui.launcher.LauncherActivity
import com.base.module.base.data.ActivityDefinition
import com.base.module.base.scenario.BaseScenario
import timber.log.Timber

object UiScenario: BaseScenario() {
    override fun findActivity(name: String?): Boolean {name?.let {
        if (findActivity(name, launcherActivities)) {
            return true
        }
    }
        return false
    }
    override fun getHomeActivity(): String? {
        return MainActivity::class.java.name
    }

    private lateinit var _launcherActivity: ActivityDefinition
    override fun setActivities() {
        _launcherActivity = ActivityDefinition(LauncherActivity::class.java.name)
            .setPermissions(
                arrayOf(),
                arrayOf()
            )
            .setNewIntent(true)
        setLauncherActivities()
    }

    private fun setLauncherActivities() {
        Timber.d("setLauncherActivities")
        addLauncherActivities(_launcherActivity)
    }

    private var _launcherActivities: MutableList<ActivityDefinition>? = null
    private val launcherActivities get() = _launcherActivities!!
    private fun addLauncherActivities(definition: ActivityDefinition?): UiScenario {
        if (_launcherActivities == null) {
            _launcherActivities = ArrayList()
        }
        if (definition != null) {
            launcherActivities.add(definition)
        }
        return this
    }
}