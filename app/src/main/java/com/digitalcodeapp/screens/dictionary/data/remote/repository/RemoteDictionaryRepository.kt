package com.digitalcodeapp.screens.dictionary.data.remote.repository

import com.digitalcodeapp.screens.dictionary.data.remote.states.RemotePagedDictionaryTermsListResult

interface RemoteDictionaryRepository {

    suspend fun getPagedDictionaryTermsList(
        query: String,
        startingPosition: Int,
        limit: Int
    ): RemotePagedDictionaryTermsListResult
}