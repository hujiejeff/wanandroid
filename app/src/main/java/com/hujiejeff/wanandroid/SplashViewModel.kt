package com.hujiejeff.wanandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SplashViewModel: BaseViewModel() {
    val helloString = MutableLiveData<String>()
    val helloStr2: MutableStateFlow<String?> = MutableStateFlow(null)

    fun change() {
//        helloString.value = "change"
        helloStr2.value = "change"
    }
}