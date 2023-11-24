package com.digitalcodeapp.screens.dictionary.di

import com.digitalcodeapp.screens.dictionary.data.remote.repository.RemoteDictionaryRepository
import com.digitalcodeapp.screens.dictionary.data.repository.DictionaryRepositoryImpl
import com.digitalcodeapp.screens.dictionary.domain.repository.DictionaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideDictionaryRepository(remoteDictionaryRepository: RemoteDictionaryRepository): DictionaryRepository =
        DictionaryRepositoryImpl(remoteDictionaryRepository)
}