package com.mindvalley.pinterest.injection

import android.app.Application
import com.mindvalley.App
import com.mindvalley.pinterest.injection.module.ApplicationModule
import com.mindvalley.pinterest.injection.module.PresentationModule
import com.mindvalley.pinterest.injection.module.UIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (ApplicationModule::class), (UIModule::class),
    (PresentationModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: App)

}