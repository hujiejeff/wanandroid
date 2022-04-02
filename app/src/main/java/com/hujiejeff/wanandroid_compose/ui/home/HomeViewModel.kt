package com.hujiejeff.wanandroid_compose.ui.home

import android.util.Log
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

    val mainScreenState =
        MutableStateFlow(MainScreenState(emptyList(), emptyList(), LoadingState.Loading))

    fun loadBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            val bannerBeanRep = DataModel.getBanners()
            if (bannerBeanRep.errorCode != -1) {
                _banners.addAll(bannerBeanRep.data!!)
            }
        }
    }

    fun refreshArticles() {
        refreshViewState(LoadingState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val articleBeanRep = DataModel.getMainArticles(1)
            Log.d("hujie", "refreshArticles: " + articleBeanRep.toString())
            if (articleBeanRep.errorCode != -1) {
                _articles.addAll(articleBeanRep.data!!.datas)
                refreshViewState(LoadingState.Success)
            }
        }
    }

    fun loadMoreArticles() {
        refreshArticles()
    }

    private fun loadArticles() {

    }

    private fun refreshViewState(loadingState: LoadingState) {
        mainScreenState.value =
            MainScreenState(_banners, _articles, loadingState)
    }
}