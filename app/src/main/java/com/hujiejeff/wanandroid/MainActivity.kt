package com.hujiejeff.wanandroid

import com.hujiejeff.wanadnroid.module.base.base.BaseActivity
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanadnroid.module.base.utils.startBySlide
import com.hujiejeff.wanandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun ActivityMainBinding.initView() {
        btnJumpLogin.setOnClickListener {
            startBySlide(RouteMap.Login.LOGIN_ACTIVITY)
        }
    }

    override fun initData() {
        mBinding.btnJumpLogin.text = "跳转的公路"
    }

}