package com.hujiejeff.wanandroid_compose.network

import com.hujiejeff.base.data.remote.GlobalHttp

object DataModel: WanAndroidApi by GlobalHttp.getApi()
