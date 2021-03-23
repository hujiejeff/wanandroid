package com.hujiejeff.wanadnroid.module.base.data.remote.bean

data class BaseBean<T>(val data: T?, val errorCode: Int = 0, val errorMsg: String = "")