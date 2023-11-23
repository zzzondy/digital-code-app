package com.digitalcodeapp.screens.quiz.di

import com.digitalcodeapp.screens.quiz.domain.repository.QuizRepository
import com.digitalcodeapp.screens.quiz.domain.use_cases.ObtainAllQuizQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideObtainAllQuizQuestionsUseCase(quizRepository: QuizRepository): ObtainAllQuizQuestionsUseCase =
        ObtainAllQuizQuestionsUseCase(quizRepository)
}
