package com.digitalcodeapp.screens.quiz.di

import com.digitalcodeapp.screens.quiz.data.remote.repository.RemoteQuizRepository
import com.digitalcodeapp.screens.quiz.data.repository.QuizRepositoryImpl
import com.digitalcodeapp.screens.quiz.domain.repository.QuizRepository
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
    fun provideQuizRepository(remoteQuizRepository: RemoteQuizRepository): QuizRepository =
        QuizRepositoryImpl(remoteQuizRepository)
}