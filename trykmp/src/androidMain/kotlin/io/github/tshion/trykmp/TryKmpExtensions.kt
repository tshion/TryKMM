package io.github.tshion.trykmp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.ContinuationInterceptor

public fun TryKmp.timer(): Flow<String> = flow {
    val dispatcher = currentCoroutineContext()[ContinuationInterceptor]
    while (true) {
        val text = time(suffix = " $dispatcher")
        println(text)
        emit(text)
        delay(2_500)
    }
}.flowOn(Dispatchers.Default)
