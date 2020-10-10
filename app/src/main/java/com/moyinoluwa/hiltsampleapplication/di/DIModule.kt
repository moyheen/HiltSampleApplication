package com.moyinoluwa.hiltsampleapplication.di

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
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
abstract class ActivityModule {

    @Binds
    abstract fun bindRepository(sampleRepository: HiltSampleRepositoryImpl): HiltSampleRepository

    @Binds
    abstract fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
}

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://google.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesHiltSampleService(retrofit: Retrofit): HiltSampleService =
        retrofit.create(HiltSampleService::class.java)
}
