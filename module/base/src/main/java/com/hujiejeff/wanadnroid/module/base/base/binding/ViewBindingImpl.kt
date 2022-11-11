package com.hujiejeff.wanadnroid.module.base.base.binding

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


/**
 * 已知固定继承代码结构下无侵入性的通过以下方式委托获取ViewBinding，仅支持kotlin委托
 * 必须继承或委托方式使用
 * SomeClass(itemView): IViewBinding<FiveNewsItemSytleRightPicBinding> by object :ViewBindingImp<FiveNewsItemSytleRightPicBinding>(rootView | layoutInflater){}
 */

abstract class ViewBindingImpl<VB : ViewBinding> constructor(private val _rootView: View?) :
    IViewBinding<VB> {
    private lateinit var _layoutInflater: LayoutInflater

    constructor(layoutInflater: LayoutInflater) : this(null) {
        _layoutInflater = layoutInflater
    }

    private val _mBinding: VB by lazy {
        val type = javaClass.genericSuperclass
        val vbClass = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>

        if (_rootView != null) {
            val method = vbClass.getDeclaredMethod("bind", View::class.java)
            method.invoke(null, rootView) as VB
        } else {
            require(this::_layoutInflater.isInitialized) {
                "rootView and layoutInflater must provide one"
            }
            val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
            method.invoke(null, _layoutInflater) as VB
        }
    }

    override val mBinding: VB
        get() = _mBinding

    override val rootView: View
        get() = _rootView ?: _mBinding.root
}