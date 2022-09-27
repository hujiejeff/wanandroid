package com.hujiejeff.wanadnroid.module.base.base

sealed class LoadingState {
    object Success: LoadingState()
    object Loading: LoadingState()
    object Failed: LoadingState()
    object Refreshing: LoadingState()
    object LoadingMore: LoadingState()
    object LoadingMoreSuccess: LoadingState()
    object LoadingMoreFailed: LoadingState()
}

