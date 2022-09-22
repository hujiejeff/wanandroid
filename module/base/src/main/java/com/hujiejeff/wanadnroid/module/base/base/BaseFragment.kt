package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hujiejeff.wanadnroid.module.base.utils.setFragmentContentView
import java.lang.reflect.ParameterizedType

open class BaseFragment<VB: ViewBinding>: Fragment() {
    protected val mBinding: VB by lazy {
        val type = javaClass.genericSuperclass
        val vbClass = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }
    open  fun VB.initView(){}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding.initView()
        return mBinding.root
    }

}