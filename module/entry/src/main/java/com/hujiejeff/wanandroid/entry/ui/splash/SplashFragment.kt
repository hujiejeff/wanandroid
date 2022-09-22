package com.hujiejeff.wanandroid.entry.ui.splash

import androidx.navigation.fragment.findNavController
import com.hujiejeff.wanadnroid.module.base.base.BaseFragment
import com.hujiejeff.wanandroid.entry.R
import com.hujiejeff.wanandroid.entry.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun onResume() {
        super.onResume()
        mBinding.root.postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 1000)
    }
}