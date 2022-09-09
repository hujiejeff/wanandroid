package com.hujiejeff.wanadnroid.module.base.base

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmActivity<V: ViewBinding, VM: BaseViewModel>: BaseActivity<V>() {
    val mViewModel: VM by lazy {
        val type = javaClass.genericSuperclass
        val vmClass: Class<VM> = (type as ParameterizedType).actualTypeArguments[1] as Class<VM>
        ViewModelProvider(this, defaultViewModelProviderFactory).get(vmClass)
    }

    override fun initData() {
        super.initData()
        mViewModel.subscribe()
    }

    open fun VM.subscribe() {}
}