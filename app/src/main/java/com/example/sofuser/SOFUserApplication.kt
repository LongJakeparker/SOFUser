package com.example.sofuser

import android.annotation.SuppressLint
import android.app.Application

class SOFUserApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var injector: Injector

        fun getInjector(): Injector {
            return injector
        }
    }

    override fun onCreate() {
        super.onCreate()
        injector = Injector(applicationContext)
    }
}