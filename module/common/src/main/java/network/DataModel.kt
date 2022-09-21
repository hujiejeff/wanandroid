package network

import com.hujiejeff.wanadnroid.module.base.data.remote.GlobalHttp
const val BASE_URL = "https://www.wanandroid.com"
object DataModel: WanAndroidApi by GlobalHttp.build(BASE_URL).getApi()