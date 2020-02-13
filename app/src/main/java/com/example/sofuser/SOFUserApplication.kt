package com.example.sofuser

import android.annotation.SuppressLint
import android.app.Application

class SOFUserApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var injector: Injector
        private lateinit var mInstance: SOFUserApplication

        fun getInjector(): Injector {
            return injector
        }

        fun getInstance(): SOFUserApplication {
            return mInstance
        }
    }

    enum class AppStatus{
        START,
        PAUSE,
        STOP
    }

    var appStatus: AppStatus = AppStatus.STOP

    override fun onCreate() {
        super.onCreate()
        injector = Injector(applicationContext)
        mInstance = this
    }
}