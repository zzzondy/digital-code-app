package com.digitalcodeapp.screens.dictionary.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.digitalcodeapp.screens.dictionary.data.remote.repository.RemoteDictionaryRepository
import com.digitalcodeapp.screens.dictionary.data.remote.states.RemotePagedDictionaryTermsListResult
import com.digitalcodeapp.screens.dictionary.data.utils.toDomain
import com.digitalcodeapp.screens.dictionary.domain.models.DictionaryTerm

class DictionaryTermsPagingSource(
    private val searchQuery: String,
    private val remoteDictionaryRepository: RemoteDictionaryRepository
) : PagingSource<Int, DictionaryTerm>() {

    override fun getRefreshKey(state: PagingState<Int, DictionaryTerm>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DictionaryTerm> {
        val startingPosition = (params.key ?: 1)
        val result = remoteDictionaryRepository.getPagedDictionaryTermsList(
            searchQuery,
            startingPosition,
            params.loadSize
        )


        return try {
            when (result) {
                is RemotePagedDictionaryTermsListResult.Success -> {
                    if (result.dictionaryTerms.isEmpty() && startingPosition == 1) {
                        return LoadResult.Error(Exception())
                    }

                    LoadResult.Page(
                        data = result.dictionaryTerms.map { it.toDomain() },
                        prevKey = if (startingPosition == 1) null else startingPosition - params.loadSize,
                        nextKey = if (result.dictionaryTerms.isEmpty()) null else startingPosition + params.loadSize
                    )
                }

                is RemotePagedDictionaryTermsListResult.Error -> LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}