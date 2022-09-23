package com.hujiejeff.wanandroi.module.main

import androidx.lifecycle.viewModelScope
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel
import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import network.DataModel
import network.bean.ArticleBean
import network.bean.BannerBean
import network.bean.PageBean

class MainViewModel : BaseViewModel() {
    val dataState: MutableStateFlow<MainDataState> =
        MutableStateFlow(MainDataState(emptyList(), emptyList()))

    fun requestData() {
        viewModelScope.launch {
            val bannerJob = async(Dispatchers.IO) { DataModel.getBanners() }
            val articlesJob = async(Dispatchers.IO) { DataModel.getMainArticles(0) }
            val bannerBean: BaseBean<List<BannerBean>> = bannerJob.await()
            val articlesBean: BaseBean<PageBean<ArticleBean>> = articlesJob.await()
            refreshDataState(bannerBean.data!!, articlesBean.data!!.datas)
        }
    }

    private fun refreshDataState(banner: List<BannerBean>, articles: List<ArticleBean>) {
        dataState.value = MainDataState(banner, articles)
    }

    class MainDataState(val banner: List<BannerBean>, val articles: List<ArticleBean>)
}