package com.hujiejeff.wanandroid_compose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hujiejeff.wanandroid_compose.network.DataModel
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import com.hujiejeff.wanandroid_compose.ui.model.MainScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _banners = mutableListOf<BannerBean>()
    private val _articles = mutableListOf<ArticleBean>()
    private var page = 1

    val mainScreenState =
        MutableStateFlow(MainScreenState(emptyList(), emptyList(), LoadingState.UnInit))

    /**
     * 加载Banner
     */
    fun loadBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            val bannerBeanRep = DataModel.getBanners()
            if (bannerBeanRep.errorCode != -1) {
                _banners.addAll(bannerBeanRep.data!!)
            }
        }
    }

    /**
     * 刷新
     */
    fun firstLoadArticles() {
        page = 1
        refreshViewState(LoadingState.Loading)
        loadArticles(page)
    }

    fun refreshLoadArticles() {
        page = 1
        refreshViewState(LoadingState.RefreshLoading)
        loadArticles(page)
    }

    /**
     * 加载更多
     */
    fun loadMoreArticles() {
        refreshViewState(LoadingState.LoadingMore)
        loadArticles(++page)
    }

    /**
     * 加载首页图片
     */
    private fun loadArticles(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val articleBeanRep = DataModel.getMainArticles(page)
            if (articleBeanRep.errorCode != -1) {
                if (page == 1) {
                    _articles.clear()
                }
                _articles.addAll(articleBeanRep.data!!.datas)
                when(mainScreenState.value.loadingState) {
                    LoadingState.Loading,LoadingState.LoadingMore -> refreshViewState(LoadingState.LoadSuccess)
                    LoadingState.RefreshLoading -> refreshViewState(LoadingState.RefreshSuccess)
                    else -> {}
                }
            } else {
                refreshViewState(LoadingState.Error)
            }
            delay(100)
            refreshViewState(LoadingState.IDLE)
        }
    }

    private fun refreshViewState(loadingState: LoadingState) {
        mainScreenState.value =
            MainScreenState(_banners, _articles, loadingState)
    }
}