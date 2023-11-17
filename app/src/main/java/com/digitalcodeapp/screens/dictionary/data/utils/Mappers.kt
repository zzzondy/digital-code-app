package com.digitalcodeapp.screens.dictionary.data.utils

import com.digitalcodeapp.screens.dictionary.data.remote.models.RemoteDictionaryTerm
import com.digitalcodeapp.screens.dictionary.domain.models.DictionaryTerm

fun RemoteDictionaryTerm.toDomain() = DictionaryTerm(
    id = id,
    label = label,
    description = description,
)