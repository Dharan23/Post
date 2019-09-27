package com.codeutility.post.ui.base

import kotlinx.coroutines.*

fun <T> CustomLazy(b: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            b.invoke(this)
        }
    }
}

