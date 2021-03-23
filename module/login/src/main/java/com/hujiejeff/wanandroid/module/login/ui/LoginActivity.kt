package com.hujiejeff.wanandroid.module.login.ui

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.hujiejeff.wanadnroid.module.base.base.BaseActivity
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanandroid.module.login.databinding.LoginActivityLoginBinding
import com.hujiejeff.wanandroid.module.login.ui.login.LoginViewModel

@Route(path = RouteMap.Login.LOGIN_ACTIVITY)
class LoginActivity : BaseActivity<LoginActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun LoginActivityLoginBinding.initView() {

    }

    override fun initData() {

    }
}