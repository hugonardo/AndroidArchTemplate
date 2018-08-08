package net.hugonardo.archtemplate

import android.content.Context
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.squareup.leakcanary.LeakCanary
import com.tspoon.traceur.Traceur
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric
import net.hugonardo.archtemplate.di.DaggerApplicationComponent
import timber.log.Timber

class TemplateApplication : DaggerApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupFabricKits()
        setupLeakCanary()
        plantTimberTrees()
        setupTraceur()
    }

    private fun setupFabricKits() {
        val crashlytics = Crashlytics.Builder()
                .core(CrashlyticsCore.Builder()
                        .disabled(BuildConfig.DEBUG)
                        .build()
                )
                .build()
        Fabric.with(this, crashlytics)
    }

    private fun setupLeakCanary() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return
            }
            LeakCanary.install(this)
        }
    }

    private fun plantTimberTrees() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupTraceur() {
        if (BuildConfig.DEBUG) {
            Traceur.enableLogging()
        }
    }

    override fun applicationInjector(): AndroidInjector<out TemplateApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }
}
