package com.hujiejeff.wanandroid.module.project.ui.project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hujiejeff.wanadnroid.module.base.base.BaseFragment
import com.hujiejeff.wanadnroid.module.base.base.BaseMvvmFragment
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanandroid.module.project.databinding.ProjectFragmentHomeBinding
import com.hujiejeff.wanandroid.module.project.ui.project.sub.ProjectSubFragment
import network.bean.TreeBean

@Route(path = RouteMap.Project.HOME_FRAGMENT)
class ProjectFragment : BaseMvvmFragment<ProjectFragmentHomeBinding, ProjectViewModel>() {
    private val trees: MutableList<TreeBean> = mutableListOf()
    override fun ProjectFragmentHomeBinding.initView() {
        vpProject.adapter = PageAdapter(trees, childFragmentManager, viewLifecycleOwner.lifecycle)
        TabLayoutMediator(
            tab,
            vpProject,
            true,
            true
        ) { tab, position ->
            tab.text = trees[position].name
        }.attach()
        mViewModel.treeListBean.observe(this@ProjectFragment) {
            trees.addAll(it)
            mBinding.vpProject.adapter?.notifyDataSetChanged()
        }
    }

    override suspend fun ProjectViewModel.subscribe() {

    }

    inner class PageAdapter(
        private val _trees: MutableList<TreeBean>,
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int = _trees.size

        override fun createFragment(position: Int): Fragment {
            return ProjectSubFragment.newInstance(_trees[position])
        }
    }
}