package com.hujiejeff.wanandroid_compose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hujiejeff.wanandroid_compose.network.DataModel
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import com.hujiejeff.wanandroid_compose.ui.model.MainScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _banners = mutableListOf<BannerBean>()
    private val _articles = mutableListOf<ArticleBean>()
    private var page = 1

    val mainScreenState =
        MutableStateFlow(MainScreenState(emptyList(), emptyList(), LoadingState.Loading))

    /**
     * 加载Banner
     */
    fun loadBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            val bannerBeanRep = DataModel.getBanners()
            if (bannerBeanRep.errorCode != -1) {
                _banners.addAll(bannerBeanRep.data!!)
                refreshViewState(LoadingState.Success)
            }
        }
    }

    /**
     * 刷新
     */
    fun firstLoadArticles(refresh: Boolean) {
        page = 1
        refreshViewState(if (refresh) LoadingState.RefreshLoading else LoadingState.Loading)
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
                refreshViewState(LoadingState.Success)
            } else {
                refreshViewState(LoadingState.Error)
            }
        }
    }

    private fun refreshViewState(loadingState: LoadingState) {
        mainScreenState.value =
            MainScreenState(_banners, _articles, loadingState)
    }
}