package com.example.threedapp

import android.app.Application
import android.content.Context
import com.example.threedapp.di.AppComponent
import com.example.threedapp.di.DaggerAppComponent
import com.example.threedapp.base.IntentHendler

class ThreeDAplication:Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is ThreeDAplication -> appComponent
        else -> applicationContext.appComponent
    }