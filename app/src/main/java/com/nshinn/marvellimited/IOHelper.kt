package com.nshinn.marvellimited

import kotlinx.coroutines.*

fun <T> CoroutineScope.runOnIOThread(block: suspend CoroutineScope.() -> T): Pair<Deferred<T>, CoroutineScope> {
    return Pair(async(Dispatchers.IO, CoroutineStart.LAZY) {
        block()
    }, this)
}

infix fun <T> Pair<Deferred<T>, CoroutineScope>.deferOnUIThread(block: (T?, e: Exception?) -> Unit) {
    this@deferOnUIThread.second.launch(Dispatchers.Default) {
        var exception: Exception? = null
        var result: T? = null
        try {
            result = this@deferOnUIThread.first.await()
        } catch (e: CancellationException) {
            return@launch
        } catch (e: Exception) {
            exception = e
        }
        block(result, exception)
    }
}