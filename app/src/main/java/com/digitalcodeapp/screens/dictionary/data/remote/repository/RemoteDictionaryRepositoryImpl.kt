package com.digitalcodeapp.screens.dictionary.data.remote.repository

import com.digitalcodeapp.screens.dictionary.data.remote.models.RemoteDictionaryTerm
import com.digitalcodeapp.screens.dictionary.data.remote.states.RemotePagedDictionaryTermsListResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RemoteDictionaryRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteDictionaryRepository {

    override suspend fun getPagedDictionaryTermsList(
        query: String,
        startingPosition: Int,
        limit: Int
    ): RemotePagedDictionaryTermsListResult =
        RemotePagedDictionaryTermsListResult.Success(
            fireStore.collection(DICTIONARY_COLLECTION)
                .orderBy(ID)
                .whereArrayContains(LABEL_BY_SYMBOLS, query)
                .startAt(startingPosition)
                .limit(limit.toLong())
                .get()
                .await()
                .toObjects(RemoteDictionaryTerm::class.java)
        )

    companion object {
        private const val DICTIONARY_COLLECTION = "dict"

        private const val ID = "id"
        private const val LABEL_BY_SYMBOLS = "label_by_symbols"
    }
}