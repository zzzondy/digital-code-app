package com.digitalcodeapp.screens.dictionary.domain.repository

import androidx.paging.PagingData
import com.digitalcodeapp.screens.dictionary.domain.models.DictionaryTerm
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    fun getPagedDictionaryTermsList(query: String): Flow<PagingData<DictionaryTerm>>
}