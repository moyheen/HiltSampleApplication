package com.moyinoluwa.hiltsampleapplication

import android.app.Activity
import com.moyinoluwa.hiltsampleapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class HiltSampleApplication : DaggerApplication(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private val appComponent: AndroidInjector<HiltSampleApplication> by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? = activityInjector
}
