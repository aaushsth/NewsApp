@file:Suppress("NOTHING_TO_INLINE")

package com.outcode.newsapp.data.handler

import timber.log.Timber

inline fun logger(msg: String?) {
    Timber.d(msg)
}

inline fun loggerE(msg: String?) {
    Timber.e(msg)
}

inline fun logger(msg: String?, tag: String) {
     Timber.tag(tag).d(msg)
}
