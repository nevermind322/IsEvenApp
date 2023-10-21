package com.example.di.network

import com.example.data.even.NetworkEvenDataSource
import com.example.data.fact.NetworkFactDatasource
import com.example.network.even.RetrofitEvenDataSource
import com.example.network.fact.RetrofitFactDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun getFactDataSource(impl : RetrofitFactDataSource) : NetworkFactDatasource

    @Binds
    fun getEvenDataSource( impl : RetrofitEvenDataSource) : NetworkEvenDataSource

}