package com.hujiejeff.wanadnroid.module.base.base.adapter

import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class BaseViewBindingHolder<VB : ViewBinding>(val mBinding: VB) : BaseViewHolder(mBinding.root)