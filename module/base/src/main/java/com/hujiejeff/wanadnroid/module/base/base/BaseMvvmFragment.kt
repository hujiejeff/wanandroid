package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.subscribe()
            }
        }

    }

    open suspend fun VM.subscribe() {}
}