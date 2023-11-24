package com.digitalcodeapp.screens.dictionary.data.remote.states

import com.digitalcodeapp.screens.dictionary.data.remote.models.RemoteDictionaryTerm

sealed class RemotePagedDictionaryTermsListResult {

    data class Success(val dictionaryTerms: List<RemoteDictionaryTerm>) :
        RemotePagedDictionaryTermsListResult()

    data object Error : RemotePagedDictionaryTermsListResult()
}
