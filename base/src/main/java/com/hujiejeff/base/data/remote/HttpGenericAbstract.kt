package com.hujiejeff.base.data.remote


import com.hujiejeff.base.utils.getSuperClassGenericClass

abstract class HttpGenericAbstract<T>: HttpAbstract() {
    val api: T by lazy {
        getRetrofit().create(getSuperClassGenericClass(this))
    }
}