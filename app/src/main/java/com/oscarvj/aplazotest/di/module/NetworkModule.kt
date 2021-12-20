package com.oscarvj.aplazotest.di.module

import com.oscarvj.aplazotest.retrofit.ClientRetrofit
import com.oscarvj.aplazotest.data.network.ApiClient
import com.oscarvj.aplazotest.di.manager.RetrofitManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val clientRetrofit: ClientRetrofit = ClientRetrofit()

    @Singleton
    @Provides
    fun provideRetrofit(): RetrofitManager {
        return RetrofitManager(clientRetrofit.getClient().create(ApiClient::class.java))
    }
}