package com.oscarvj.aplazotest.di.module

import android.app.Application
import com.oscarvj.aplazotest.di.manager.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideManagerView(app: Application): NavigationManager = NavigationManager(app)
}