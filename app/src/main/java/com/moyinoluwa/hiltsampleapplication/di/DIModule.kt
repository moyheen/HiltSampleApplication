package com.moyinoluwa.hiltsampleapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moyinoluwa.hiltsampleapplication.HiltSampleViewModel
import com.moyinoluwa.hiltsampleapplication.MainActivity
import com.moyinoluwa.hiltsampleapplication.data.HiltSampleRepository
import com.moyinoluwa.hiltsampleapplication.data.HiltSampleRepositoryImpl
import com.moyinoluwa.hiltsampleapplication.data.remote.HiltSampleService
import com.moyinoluwa.hiltsampleapplication.util.GlideImageLoader
import com.moyinoluwa.hiltsampleapplication.util.ImageLoader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindRepository(sampleRepository: HiltSampleRepositoryImpl): HiltSampleRepository

    @Binds
    abstract fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
}

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HiltSampleViewModel::class)
    internal abstract fun hiltSampleViewModel(viewModel: HiltSampleViewModel): ViewModel
}

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Reusable
    @JvmStatic
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Reusable
    @JvmStatic
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://google.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun providesHiltSampleService(retrofit: Retrofit): HiltSampleService =
        retrofit.create(HiltSampleService::class.java)
}
