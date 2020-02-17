package com.example.sofuser

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SOFUserApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var injector: Injector
        private lateinit var instance: SOFUserApplication

        fun getInjector(): Injector {
            return injector
        }

        fun getInstance(): SOFUserApplication {
            return instance
        }

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        injector = Injector(applicationContext)
        instance = this
    }
}