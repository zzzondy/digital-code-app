package com.digitalcodeapp.screens.dictionary.data.remote.models

import androidx.annotation.Keep

@Keep
data class RemoteDictionaryTerm(
    val id: Long = 0,
    val label: String = "",
    val description: String = "",
)