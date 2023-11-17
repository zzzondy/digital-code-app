package com.digitalcodeapp.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher

    val unconfined: CoroutineDispatcher
}