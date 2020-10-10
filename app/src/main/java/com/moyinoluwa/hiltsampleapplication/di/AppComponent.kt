package com.moyinoluwa.hiltsampleapplication.di

import android.app.Application
import com.moyinoluwa.hiltsampleapplication.HiltSampleApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        NetworkModule::class]
)
interface AppComponent : AndroidInjector<HiltSampleApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
