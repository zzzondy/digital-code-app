package com.digitalcodeapp.screens.quiz.di

import com.digitalcodeapp.screens.quiz.data.remote.repository.RemoteQuizRepository
import com.digitalcodeapp.screens.quiz.data.remote.repository.RemoteQuizRepositoryImpl
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
    fun provideRemoteQuizRepository(fireStore: FirebaseFirestore): RemoteQuizRepository =
        RemoteQuizRepositoryImpl(fireStore)
}