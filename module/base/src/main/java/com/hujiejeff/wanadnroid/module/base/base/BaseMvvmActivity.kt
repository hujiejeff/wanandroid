package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseMvvmActivity<V: ViewBinding, VM: BaseViewModel>: BaseActivity<V>() {
    open fun VM.subscribe() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}