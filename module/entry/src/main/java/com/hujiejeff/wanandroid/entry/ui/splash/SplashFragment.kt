package com.hujiejeff.wanandroid.entry.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hujiejeff.wanadnroid.module.base.base.BaseFragment
import com.hujiejeff.wanandroid.entry.R
import com.hujiejeff.wanandroid.entry.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun onResume() {
        super.onResume()
        mBinding.root.postDelayed({
            findNavController().navigate("home")
        }, 1000)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}