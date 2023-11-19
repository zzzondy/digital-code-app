package com.digitalcodeapp.screens.dictionary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.digitalcodeapp.common.dispatchers.DispatchersProvider
import com.digitalcodeapp.screens.dictionary.domain.use_cases.ObtainPagedDictionaryTermsListUseCase
import com.digitalcodeapp.screens.dictionary.presentation.states.DictionaryScreenAction
import com.digitalcodeapp.screens.dictionary.presentation.states.DictionaryScreenEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryScreenViewModel @Inject constructor(
    obtainPagedDictionaryTermsList: ObtainPagedDictionaryTermsListUseCase,
    dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val queryChannel = Channel<String>()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val dictionaryTerms = queryChannel.receiveAsFlow()
        .distinctUntilChanged()
        .sample(SEARCHING_PERIOD)
        .flatMapLatest { searchingQuery ->
            obtainPagedDictionaryTermsList(searchingQuery)
        }
        .flowOn(dispatchersProvider.io)
        .cachedIn(viewModelScope)

    private val _effect = MutableSharedFlow<DictionaryScreenEffect>()
    val effect = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            queryChannel.send("")
        }
    }

    fun onAction(action: DictionaryScreenAction) {
        when (action) {
            is DictionaryScreenAction.OnBackArrowClicked -> onBackButtonClicked()

            is DictionaryScreenAction.OnRefreshButtonClicked -> onRefreshButtonClicked()

            is DictionaryScreenAction.OnEnteredNewQuery -> onEnteredNewQuery(action.query)
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            _effect.emit(DictionaryScreenEffect.NavigateBack)
        }
    }

    private fun onRefreshButtonClicked() {
        viewModelScope.launch {
            _effect.emit(DictionaryScreenEffect.RefreshData)
        }
    }

    private fun onEnteredNewQuery(query: String) {
        viewModelScope.launch {
            queryChannel.send(query)
        }
    }

    companion object {
        private const val SEARCHING_PERIOD: Long = 500
    }
}