package com.rickandmorty.app

import android.app.Application
import com.rickandmorty.data.serviceLocator.dataModule
import com.rickandmorty.ui.serviceLocator.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application()  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, uiModule)
        }
    }

}