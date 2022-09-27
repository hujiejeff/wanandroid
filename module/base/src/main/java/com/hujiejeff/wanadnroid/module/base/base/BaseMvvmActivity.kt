package com.hujiejeff.wanadnroid.module.base.base

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {
    val mViewModel: VM by lazy {
        val type = javaClass.genericSuperclass
        val vmClass: Class<VM> = (type as ParameterizedType).actualTypeArguments[1] as Class<VM>
        ViewModelProvider(
            this.viewModelStore,
            defaultViewModelProviderFactory,
            MutableCreationExtras(defaultViewModelCreationExtras).apply { })[vmClass]
    }

    override fun initData() {
        super.initData()
        mViewModel.subscribe()
    }

    open fun VM.subscribe() {}
}