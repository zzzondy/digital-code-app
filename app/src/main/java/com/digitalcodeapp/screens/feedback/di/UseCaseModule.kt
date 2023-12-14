package com.digitalcodeapp.screens.feedback.di

import com.digitalcodeapp.screens.feedback.domain.repository.FeedbackRepository
import com.digitalcodeapp.screens.feedback.domain.use_cases.SendFeedbackUseCase
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
    fun provideSendFeedbackUseCase(feedbackRepository: FeedbackRepository): SendFeedbackUseCase =
        SendFeedbackUseCase(feedbackRepository)

}