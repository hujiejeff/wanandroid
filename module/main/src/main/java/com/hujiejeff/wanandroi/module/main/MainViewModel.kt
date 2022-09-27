package com.hujiejeff.wanandroi.module.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel
import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import network.DataModel
import network.bean.ArticleBean
import network.bean.BannerBean
import network.bean.PageBean

class MainViewModel : BaseViewModel() {
    val dataState: MutableStateFlow<MainDataState> by lazy {
        MutableStateFlow(MainDataState(emptyList())).apply {
            startUp()
        }
    }

    val banner = MutableLiveData<List<BannerBean>>()

    private fun startUp() {
        viewModelScope.launch {
            pageStateFlow.collect {page->
                var bannerJob:
                        Deferred<BaseBean<List<BannerBean>>>? = null
                if (page.value == 0) {
                    bannerJob = async(Dispatchers.IO) { DataModel.getBanners() }
                }
                val articlesJob = async(Dispatchers.IO) { DataModel.getMainArticles(page.value) }
                bannerJob?.run {
                    val bannerBean: BaseBean<List<BannerBean>> = bannerJob.await()
                    banner.value = bannerBean.data!!
                }
                val articlesBean: BaseBean<PageBean<ArticleBean>> = articlesJob.await()
                if (page.value == 0) {
                    refreshDataState(articlesBean.data!!.datas)
                } else {
                    val list = mutableListOf<ArticleBean>().apply {
                        addAll(dataState.value.articles)
                        addAll(articlesBean.data!!.datas)
                    }
                    refreshDataState(list)
                }
            }
        }
    }

    private fun refreshDataState(articles: List<ArticleBean>) {
        dataState.value = MainDataState(articles)
    }

    class MainDataState(val articles: List<ArticleBean>)
}