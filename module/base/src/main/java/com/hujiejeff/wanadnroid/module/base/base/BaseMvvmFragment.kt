package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

open class BaseMvvmFragment<VB: ViewBinding, VM: BaseViewModel>: BaseFragment<VB>() {
    val mViewModel: VM by lazy {
        val type = javaClass.genericSuperclass
        val vmClass: Class<VM> = (type as ParameterizedType).actualTypeArguments[1] as Class<VM>
        ViewModelProvider(this, defaultViewModelProviderFactory)[vmClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    open fun initData() {
        mViewModel.subscribe()
    }

    open fun VM.subscribe() {}
}