package com.digitalcodeapp.screens.dictionary.domain.use_cases

import com.digitalcodeapp.screens.dictionary.domain.repository.DictionaryRepository

class ObtainPagedDictionaryTermsListUseCase(private val dictionaryRepository: DictionaryRepository) {

    operator fun invoke() = dictionaryRepository.getPagedDictionaryTermsList()
}