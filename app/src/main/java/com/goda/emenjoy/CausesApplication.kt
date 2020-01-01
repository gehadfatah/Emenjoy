package com.goda.emenjoy

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.goda.emenjoy.model.common.ApplicationIntegration
import com.goda.emenjoy.presentation.common.LocaleHelper

class CausesApplication : Application() {

    override fun onCreate() {
        ApplicationIntegration.with(this)
        super.onCreate()
    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "ar"))
    }
    companion object {
        private var instance: CausesApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper.onAttach(this, "ar")
    }
}