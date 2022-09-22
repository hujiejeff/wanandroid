package com.hujiejeff.wanandroid.entry.ui.home

import androidx.fragment.app.Fragment
import com.hujiejeff.wanadnroid.module.base.base.BaseFragment
import com.hujiejeff.wanandroid.entry.R
import com.hujiejeff.wanandroid.entry.databinding.FragmentHomeBinding
import export.ModuleService

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val mMainFragment: Fragment by lazy { ModuleService.getMainFragment() }
    private val mProjectFragment: Fragment by lazy { ModuleService.getProjectFragment() }
    private val mTreeFragment: Fragment by lazy { ModuleService.getTreeFragment() }
    private val mMineFragment: Fragment by lazy { ModuleService.getMineFragment() }
    private lateinit var mCurrentFragment: Fragment

    override fun FragmentHomeBinding.initView() {
        showFragment(mMainFragment)
        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_main -> showFragment(mMainFragment)
                R.id.menu_item_project -> showFragment(mProjectFragment)
                R.id.menu_item_tree -> showFragment(mTreeFragment)
                R.id.menu_item_mine -> showFragment(mMineFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        if (this::mCurrentFragment.isInitialized) {
            transaction.hide(mCurrentFragment)
        }
        if (!fragment.isAdded) {
            transaction.add(R.id.fl_container, fragment, fragment.tag)
        }
        transaction.show(fragment)
        mCurrentFragment = fragment
        transaction.commitNow()
    }
}