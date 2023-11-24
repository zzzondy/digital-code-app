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

    override fun getPagedDictionaryTermsList(query: String): Flow<PagingData<DictionaryTerm>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PAGE_SIZE),
            pagingSourceFactory = { DictionaryTermsPagingSource(query, remoteDictionaryRepository) }
        ).flow

    }

    companion object {
        private const val PAGE_SIZE = 40
    }
}