package br.com.williamfranco.composewithcleanarch

import android.app.Application
import br.com.williamfranco.composewithcleanarch.src.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ComposeWithCleanArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ComposeWithCleanArchApplication)
            modules(appModule)
        }
    }
}
