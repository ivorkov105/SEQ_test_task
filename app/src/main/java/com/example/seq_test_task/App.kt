package com.example.seq_test_task

import android.app.Application
import com.example.seq_test_task.di.AppModule
import com.example.seq_test_task.di.DataModule
import com.example.seq_test_task.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModule().module,
                NetworkModule().module,
                DataModule().module
            )
        }
    }
}