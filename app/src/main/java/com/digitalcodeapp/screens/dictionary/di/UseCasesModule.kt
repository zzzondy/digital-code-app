package com.digitalcodeapp.screens.dictionary.di

import com.digitalcodeapp.screens.dictionary.domain.repository.DictionaryRepository
import com.digitalcodeapp.screens.dictionary.domain.use_cases.ObtainPagedDictionaryTermsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UseCasesModule {

    @ViewModelScoped
    @Provides
    fun provideObtainPagedDictionaryTermsListUseCase(dictionaryRepository: DictionaryRepository):
            ObtainPagedDictionaryTermsListUseCase =
        ObtainPagedDictionaryTermsListUseCase(dictionaryRepository)
}