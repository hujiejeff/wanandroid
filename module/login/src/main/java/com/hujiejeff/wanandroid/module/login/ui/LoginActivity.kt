package com.hujiejeff.wanandroid.module.login.ui

import android.view.LayoutInflater
import com.alibaba.android.arouter.facade.annotation.Route
import com.hujiejeff.wanadnroid.module.base.base.BaseActivity
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanandroid.module.login.databinding.LoginActivityLoginBinding

@Route(path = RouteMap.Login.LOGIN_ACTIVITY)
class LoginActivity : BaseActivity<LoginActivityLoginBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): LoginActivityLoginBinding =
        LoginActivityLoginBinding.inflate(layoutInflater)

    override fun LoginActivityLoginBinding.initView() {

    }

    override fun initData() {

    }
}