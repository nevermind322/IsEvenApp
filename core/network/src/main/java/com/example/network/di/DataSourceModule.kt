package com.example.network.di

import com.example.data.NetworkEvenDataSource
import com.example.network.RetrofitEvenDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun getNetworkDataSource( impl : RetrofitEvenDataSource ) : NetworkEvenDataSource

}