package com.rickandmorty.app

import android.app.Application
import com.rickandmorty.data.serviceLocator.dataModule
import org.koin.core.context.GlobalContext.startKoin
import com.rickandmorty.ui.serviceLocator.uiModule

class App : Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, uiModule)
        }
    }

}