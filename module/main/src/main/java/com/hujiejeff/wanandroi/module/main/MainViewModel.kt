package com.hujiejeff.wanandroi.module.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel
import com.hujiejeff.wanadnroid.module.base.base.ext.netLaunch
import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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

    private fun startUp() = viewModelScope.launch {

        pageStateFlow.collect { page ->
            netLaunch(
                before = {
                    Log.d("hujie", "startUp: loading")
                },
                async = {
                    DataModel.getMainArticles(page.value)
                },
                fail = {
                    Log.d("hujie", "startUp: failed")
                },
                success = {
                    if (page.value == 0) {
                        refreshDataState(it.datas)
                    } else {
                        val list = mutableListOf<ArticleBean>().apply {
                            addAll(dataState.value.articles)
                            addAll(it.datas)
                        }
                        refreshDataState(list)
                    }
                })
            netLaunch(
                async = {
                    DataModel.getBanners()
                },
                success = {
                    banner.value = it
                },
                fail = {
                    //show banner load failed message
                })
        }
    }

    private fun refreshDataState(articles: List<ArticleBean>) {
        dataState.value = MainDataState(articles)
    }

    class MainDataState(val articles: List<ArticleBean>)
}