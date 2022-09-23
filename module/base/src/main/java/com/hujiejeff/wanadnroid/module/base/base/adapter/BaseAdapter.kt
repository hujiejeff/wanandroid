package com.hujiejeff.wanadnroid.module.base.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.util.getItemView
import java.lang.reflect.ParameterizedType

abstract class BaseAdapter<T, VB : ViewBinding>(
    @LayoutRes private val layoutResId: Int,
    data: MutableList<T>? = null
) : BaseQuickAdapter<T, BaseViewBindingHolder<VB>>(layoutResId, data) {
    override fun onCreateDefViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewBindingHolder<VB> {
        val rootView = parent.getItemView(layoutResId)
        val type = javaClass.genericSuperclass
        val vbClass: Class<VB> = (type as ParameterizedType).actualTypeArguments[1] as Class<VB>
        val method = vbClass.getDeclaredMethod("bind", View::class.java)
        val binding = method.invoke(null, rootView) as VB
        return BaseViewBindingHolder(binding)
    }
}