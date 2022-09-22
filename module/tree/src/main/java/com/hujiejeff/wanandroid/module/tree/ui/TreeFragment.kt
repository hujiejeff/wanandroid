package com.hujiejeff.wanandroid.module.tree.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.hujiejeff.wanadnroid.module.base.base.BaseMvvmFragment
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanandroid.module.tree.databinding.TreeFragmentTreeBinding

@Route(path = RouteMap.Tree.HOME_FRAGMENT)
class TreeFragment: BaseMvvmFragment<TreeFragmentTreeBinding, TreeViewModel>(){
}