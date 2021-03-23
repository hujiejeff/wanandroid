package com.hujiejeff.wanadnroid.module.base.data.remote


import com.hujiejeff.wanadnroid.module.base.utils.getSuperClassGenericClass

abstract class HttpGenericAbstract<T>: HttpAbstract() {
    val api: T by lazy {
        getRetrofit().create(getSuperClassGenericClass(this))
    }
}