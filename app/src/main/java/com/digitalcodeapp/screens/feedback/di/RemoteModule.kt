package com.digitalcodeapp.screens.feedback.di

import com.digitalcodeapp.screens.feedback.data.remote.repository.RemoteFeedbackRepository
import com.digitalcodeapp.screens.feedback.data.remote.repository.RemoteFeedbackRepositoryImpl
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
    fun provideRemoteFeedbackRepositoryImpl(fireStore: FirebaseFirestore): RemoteFeedbackRepository =
        RemoteFeedbackRepositoryImpl(fireStore)
}