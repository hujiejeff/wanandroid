package com.hujiejeff.wanadnroid.module.base.base.binding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


/**
 * 已知固定继承代码结构下无侵入性的通过以下方式委托获取ViewBinding，仅支持kotlin委托，对Activity和Fragment使用尚不方便，还是老老实实用继承来写吧
 *
 * 必须继承或委托方式使用
 * SomeClass(itemView): IViewBinding<FiveNewsItemSytleRightPicBinding> by object :ViewBindingImp<FiveNewsItemSytleRightPicBinding>(rootView | layoutInflater){}
 */

abstract class ViewBindingImpl<VB : ViewBinding> :
    IViewBinding<VB> {
    private lateinit var _layoutInflater: LayoutInflater
    private var _parent: ViewGroup? = null
    private lateinit var _attachToParentArrayWrap: ArrayList<Boolean>
    private lateinit var _rootView: View

    constructor(rootView: View) {
        _rootView = rootView
    }

    constructor(context: Context, parent: ViewGroup?, attachToParent: Boolean) {
        _layoutInflater = LayoutInflater.from(context)
        _parent = parent
        _attachToParentArrayWrap = arrayListOf(attachToParent)
    }

    constructor(layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) {
        _layoutInflater = layoutInflater
        _parent = parent
        _attachToParentArrayWrap = arrayListOf(attachToParent)
    }

    constructor(layoutInflater: LayoutInflater) {
        _layoutInflater = layoutInflater
        _parent = null
    }

    private val _mBinding: VB by lazy {
        val type = javaClass.genericSuperclass
        val vbClass = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>

        if (this::_rootView.isInitialized) {
            val method = vbClass.getDeclaredMethod("bind", View::class.java)
            method.invoke(null, _rootView) as VB
        } else {
            require(this::_layoutInflater.isInitialized) {
                "rootView and layoutInflater must provide one"
            }
            if (this::_attachToParentArrayWrap.isInitialized) {
                val method = vbClass.getDeclaredMethod(
                    "inflate",
                    LayoutInflater::class.java,
                    ViewGroup::class.java,
                    Boolean::class.java
                )
                method.invoke(null, _layoutInflater, _parent, _attachToParentArrayWrap[0]) as VB
            } else {
                val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
                method.invoke(null, _layoutInflater) as VB
            }
        }
    }

    override val mBinding: VB
        get() = _mBinding

    override val mRootView: View
        get() = if (this::_rootView.isInitialized) _rootView else _mBinding.root.also {
            _rootView = it
        }
}