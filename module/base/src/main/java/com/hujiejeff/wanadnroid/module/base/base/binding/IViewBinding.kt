package com.hujiejeff.wanadnroid.module.base.base.binding

import android.view.View
import androidx.viewbinding.ViewBinding

interface IViewBinding<VB: ViewBinding> {
    val mBinding: VB
    val mRootView: View
}