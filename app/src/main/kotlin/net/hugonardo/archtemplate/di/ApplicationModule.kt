package net.hugonardo.archtemplate.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import net.hugonardo.archtemplate.TemplateApplication

@Module
class ApplicationModule {

    @Provides
    fun provideContext(application: TemplateApplication): Context = application

    @Provides
    fun provideApplication(application: TemplateApplication): Application = application
}
