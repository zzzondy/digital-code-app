package com.digitalcodeapp.common.di

import com.digitalcodeapp.common.dispatchers.DispatchersProvider
import com.digitalcodeapp.common.dispatchers.StandardDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {

    @Singleton
    @Provides
    fun provideDispatchers(): DispatchersProvider = StandardDispatchers()
}