package com.gtc.getcamp.network.di

import com.gtc.getcamp.network.BuildConfig
import com.gtc.getcamp.network.interceptor.ResultExtractorInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val READ_TIMEOUT = 30 * 1000L
private const val WRITE_TIMEOUT = 10 * 1000L
private const val CONNECTION_TIMEOUT = 10 * 1000L

// Todo: get it from properties
private const val BASE_URL = "http://getcamp2023.com/api/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideOkhttp(
        resultExtractorInterceptor: ResultExtractorInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(resultExtractorInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        moshi: Moshi,
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

}
