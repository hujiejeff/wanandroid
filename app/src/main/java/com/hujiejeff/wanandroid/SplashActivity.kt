package com.hujiejeff.wanandroid

import com.hujiejeff.wanadnroid.module.base.base.BaseMvvmActivity
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanadnroid.module.base.utils.startBySlide
import com.hujiejeff.wanandroid.databinding.ActivitySplashBinding

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
    }

}