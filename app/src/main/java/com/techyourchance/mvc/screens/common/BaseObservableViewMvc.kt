package com.techyourchance.mvc.screens.common

import java.util.*
import kotlin.collections.HashSet

abstract class BaseObservableViewMvc<ListenerType> : BaseViewMvc(),ObservableViewMvc<ListenerType>{

    val mListeners: Set<ListenerType> = hashSetOf()
        get() = Collections.unmodifiableSet(field)

    override fun registerListener(listener: ListenerType) {
        (mListeners as HashSet).add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        (mListeners as HashSet).remove(listener)
    }
}