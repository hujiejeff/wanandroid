package com.hujiejeff.wanadnroid.module.base.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hujiejeff.wanadnroid.module.base.R
import com.hujiejeff.wanadnroid.module.base.utils.setActivityContentView
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected val mBinding: VB by lazy {
        val type = javaClass.genericSuperclass
        val vbClass: Class<VB> = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }

    open fun VB.initView() {}
    open fun VB.initUIEvent() {}
    open fun initParam() {}
    open fun initData() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化intent参数
        initParam()
        setContentView(mBinding.root)
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