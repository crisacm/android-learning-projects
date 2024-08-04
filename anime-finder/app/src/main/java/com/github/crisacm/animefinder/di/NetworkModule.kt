package com.github.crisacm.animefinder.di

import com.github.crisacm.animefinder.AnimeFinderDispatcher
import com.github.crisacm.animefinder.Dispatcher
import com.github.crisacm.animefinder.data.api.interceptor.getInterceptor
import com.github.crisacm.animefinder.data.api.service.AnimeClient
import com.github.crisacm.animefinder.data.api.service.AnimeService
import com.github.crisacm.module.easyretrofit.adapter.ApiResultAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Dispatcher(AnimeFinderDispatcher.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResultAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeService(
        retrofit: Retrofit
    ): AnimeService = retrofit.create(AnimeService::class.java)

    @Provides
    @Singleton
    fun provideAnimeClient(
        animeService: AnimeService
    ): AnimeClient = AnimeClient(animeService)
}
