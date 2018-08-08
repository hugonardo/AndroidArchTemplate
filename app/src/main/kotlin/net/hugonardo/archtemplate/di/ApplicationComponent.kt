package net.hugonardo.archtemplate.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.hugonardo.archtemplate.TemplateApplication
import net.hugonardo.archtemplate.data.di.DataModule
import net.hugonardo.archtemplate.domain.di.DomainModule
import net.hugonardo.archtemplate.presentation.di.PresentationModule
import net.hugonardo.archtemplate.provider.api.di.ApiProviderModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    DomainModule::class,
    DataModule::class,
    PresentationModule::class,
    ApiProviderModule::class
])
interface ApplicationComponent : AndroidInjector<TemplateApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TemplateApplication>() {
        abstract override fun build(): ApplicationComponent
    }
}
