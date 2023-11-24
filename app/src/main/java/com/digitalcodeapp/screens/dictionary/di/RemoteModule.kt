package com.digitalcodeapp.screens.dictionary.di

import com.digitalcodeapp.screens.dictionary.data.remote.repository.RemoteDictionaryRepository
import com.digitalcodeapp.screens.dictionary.data.remote.repository.RemoteDictionaryRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class RemoteModule {

    @ViewModelScoped
    @Provides
    fun providesRemoteDictionaryRepository(fireStore: FirebaseFirestore): RemoteDictionaryRepository =
        RemoteDictionaryRepositoryImpl(fireStore)
}