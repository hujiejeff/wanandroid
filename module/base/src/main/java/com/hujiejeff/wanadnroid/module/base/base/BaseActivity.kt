package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hujiejeff.wanadnroid.module.base.R
import com.hujiejeff.wanadnroid.module.base.utils.setActivityContentView

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var mBinding: V
    open fun V.initView() {}
    open fun V.initUIEvent() {}
    open fun initParam(){}
    open fun initData() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化intent参数
        initParam()
        mBinding = setActivityContentView<V>(this, layoutInflater)!!
        mBinding.run {
            initView()
            initUIEvent()
        }
        initData()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out)
    }
}