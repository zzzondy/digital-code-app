package com.digitalcodeapp.screens.feedback.di

import com.digitalcodeapp.screens.feedback.data.remote.repository.RemoteFeedbackRepository
import com.digitalcodeapp.screens.feedback.data.repository.FeedbackRepositoryImpl
import com.digitalcodeapp.screens.feedback.domain.repository.FeedbackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideFeedbackRepository(remoteFeedbackRepository: RemoteFeedbackRepository): FeedbackRepository =
        FeedbackRepositoryImpl(remoteFeedbackRepository)
}