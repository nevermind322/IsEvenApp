package com.example.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

const val IS_EVEN_BASE_URL = "https://api.isevenapi.xyz/api/"
const val NUMBERS_BASE_URL = ""
const val IS_EVEN_API_QUALIFIER = "IsEven"
const val NUMBERS_API_QUALIFIER = "Numbers"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun getMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    @Named(IS_EVEN_API_QUALIFIER)
    fun getIsEvenRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(IS_EVEN_BASE_URL)
            .addCallAdapterFactory(ApiClassAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    @Named(NUMBERS_API_QUALIFIER)
    fun getNumbersApiRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(NUMBERS_BASE_URL)
            .addCallAdapterFactory(ApiClassAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun getNumbersService(@Named(NUMBERS_API_QUALIFIER) retrofit: Retrofit): NumbersApiService =
        retrofit.create(NumbersApiService::class.java)

    @Provides
    @Singleton
    fun getApiService(@Named(IS_EVEN_API_QUALIFIER) retrofit: Retrofit): IsEvenApiService =
        retrofit.create(IsEvenApiService::class.java)

}