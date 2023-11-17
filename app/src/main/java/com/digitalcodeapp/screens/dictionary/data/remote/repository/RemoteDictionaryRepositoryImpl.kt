package com.digitalcodeapp.screens.dictionary.data.remote.repository

import android.util.Log
import com.digitalcodeapp.screens.dictionary.data.remote.models.RemoteDictionaryTerm
import com.digitalcodeapp.screens.dictionary.data.remote.states.RemotePagedDictionaryTermsListResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RemoteDictionaryRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteDictionaryRepository {

    override suspend fun getPagedDictionaryTermsList(
        startingPosition: Int,
        limit: Int
    ): RemotePagedDictionaryTermsListResult {
        lateinit var result: RemotePagedDictionaryTermsListResult
        fireStore.collection(DICTIONARY_COLLECTION)
            .orderBy(ID)
            .startAt(startingPosition)
            .limit(limit.toLong())
            .get()
            .addOnSuccessListener {
                result =
                    RemotePagedDictionaryTermsListResult.Success(it.toObjects(RemoteDictionaryTerm::class.java))
            }
            .addOnFailureListener {
                result = RemotePagedDictionaryTermsListResult.Error
            }
            .await()

        Log.d("Result", result.toString())


        return result
    }

    companion object {
        private const val DICTIONARY_COLLECTION = "dictionary"

        private const val ID = "id"
    }
}