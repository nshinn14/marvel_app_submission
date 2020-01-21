package com.nshinn.marvellimited

import android.app.Application
import com.nshinn.marvellimited.persistence.database.UniverseDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Instantiate Database
        UniverseDatabase.getInstance(this)
    }

}