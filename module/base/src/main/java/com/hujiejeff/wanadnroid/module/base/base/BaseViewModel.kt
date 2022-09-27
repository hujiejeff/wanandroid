package com.hujiejeff.wanadnroid.module.base.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel: ViewModel() {
    val loadingStateFlow = MutableStateFlow(LoadingState.Loading)
    val pageStateFlow = MutableStateFlow(PageState(0))

    fun loadMore() {
        pageStateFlow.value++
    }
    fun refresh() {
        pageStateFlow.value = pageStateFlow.value.reset()
    }

    class PageState(val value: Int) {
        operator fun inc(): PageState {
            return PageState(value + 1)
        }

        fun reset(): PageState = PageState(0)
    }
}