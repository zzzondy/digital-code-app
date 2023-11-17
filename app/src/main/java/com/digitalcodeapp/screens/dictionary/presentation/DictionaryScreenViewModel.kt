package com.digitalcodeapp.screens.dictionary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.digitalcodeapp.common.dispatchers.DispatchersProvider
import com.digitalcodeapp.screens.dictionary.domain.use_cases.ObtainPagedDictionaryTermsListUseCase
import com.digitalcodeapp.screens.dictionary.presentation.states.DictionaryScreenAction
import com.digitalcodeapp.screens.dictionary.presentation.states.DictionaryScreenEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryScreenViewModel @Inject constructor(
    obtainPagedDictionaryTermsList: ObtainPagedDictionaryTermsListUseCase,
    dispatchersProvider: DispatchersProvider
) : ViewModel() {

    val dictionaryTerms =
        obtainPagedDictionaryTermsList().flowOn(dispatchersProvider.io).cachedIn(viewModelScope)

    private val _effect = MutableSharedFlow<DictionaryScreenEffect>()
    val effect = _effect.asSharedFlow()

    fun onAction(action: DictionaryScreenAction) {
        when (action) {
            DictionaryScreenAction.OnBackArrowClicked -> onBackButtonClicked()

            DictionaryScreenAction.OnRefreshButtonClicked -> onRefreshButtonClicked()
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
}