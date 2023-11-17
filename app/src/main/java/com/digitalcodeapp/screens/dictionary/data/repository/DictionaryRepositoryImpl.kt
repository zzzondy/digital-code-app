package com.digitalcodeapp.screens.dictionary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.digitalcodeapp.screens.dictionary.data.paging.DictionaryTermsPagingSource
import com.digitalcodeapp.screens.dictionary.data.remote.repository.RemoteDictionaryRepository
import com.digitalcodeapp.screens.dictionary.domain.models.DictionaryTerm
import com.digitalcodeapp.screens.dictionary.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow

class DictionaryRepositoryImpl(
    private val remoteDictionaryRepository: RemoteDictionaryRepository
) : DictionaryRepository {

    override fun getPagedDictionaryTermsList(): Flow<PagingData<DictionaryTerm>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 10),
            pagingSourceFactory = { DictionaryTermsPagingSource(remoteDictionaryRepository) }
        ).flow

    }


}