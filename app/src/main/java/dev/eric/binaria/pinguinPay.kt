package dev.eric.binaria

import android.app.Application
import dev.eric.binaria.di.DataModule
import dev.eric.binaria.di.DomainModule
import dev.eric.binaria.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class pinguinPay: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidContext(this@pinguinPay)
            modules(
                DomainModule,
                DataModule,
                ViewModelModule
            )
        }
    }
}