package com.hujiejeff.wanandroid

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hujiejeff.wanadnroid.module.base.base.BaseMvvmActivity
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanadnroid.module.base.utils.startBySlide
import com.hujiejeff.wanandroid.databinding.ActivitySplashBinding
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashActivity : BaseMvvmActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun ActivitySplashBinding.initView() {
        btnJumpLogin.setOnClickListener {
            startBySlide(RouteMap.Login.LOGIN_ACTIVITY)
            mViewModel.change()
        }

        mBinding.btnJumpLogin.text = "跳转的公路"
    }



    override fun SplashViewModel.subscribe() {
        helloString.observe(this@SplashActivity) {
            mBinding.btnJumpLogin.text = it
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                helloStr2.collect {
                    mBinding.btnJumpLogin.text = it
                    Toast.makeText(this@SplashActivity, "响应", Toast.LENGTH_SHORT)
                }
            }
        }
    }

}