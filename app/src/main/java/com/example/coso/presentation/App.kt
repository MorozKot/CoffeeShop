package com.example.coso.presentation

import android.app.Application
import com.example.coso.di.card
import com.example.coso.di.coffee
import com.example.coso.di.order
import com.example.coso.di.orderApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(coffee, card, order, orderApi)
        }
    }
}