package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hujiejeff.wanadnroid.module.base.utils.setFragmentContentView

open class BaseFragment<V: ViewBinding>: Fragment() {
    protected lateinit var mBinding: V
    open  fun V.initView(){}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = setFragmentContentView<V>(this,inflater, container)!!
        mBinding.initView()
        return mBinding.root
    }

}