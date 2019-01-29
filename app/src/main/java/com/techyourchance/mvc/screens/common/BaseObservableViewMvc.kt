package com.techyourchance.mvc.screens.common

import java.util.*
import kotlin.collections.HashSet

abstract class BaseObservableViewMvc<ListenerType> : BaseViewMvc(),ObservableViewMvc<ListenerType>{
    private val _mListeners: Set<ListenerType> = hashSetOf()
    val mListeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(_mListeners)

    override fun registerListener(listener: ListenerType) {
        (_mListeners as HashSet).add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        (_mListeners as HashSet).remove(listener)
    }
}