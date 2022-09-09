package com.hujiejeff.wanandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel

class SplashViewModel: BaseViewModel() {
    val helloString = MutableLiveData<String>()

    fun change() {
        helloString.value = "change"
    }
}