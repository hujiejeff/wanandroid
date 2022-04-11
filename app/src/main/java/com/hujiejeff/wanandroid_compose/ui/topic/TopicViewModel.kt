package com.hujiejeff.wanandroid_compose.ui.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hujiejeff.wanandroid_compose.network.DataModel
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import com.hujiejeff.wanandroid_compose.ui.model.TopicScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopicViewModel : ViewModel() {
    private val _articles = mutableListOf<ArticleBean>()
    private var page = 0
    var cId = 0

    val topicScreenState =
        MutableStateFlow(TopicScreenState(emptyList(), LoadingState.UnInit))

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
            delay(1000)
            val articleBeanRep = DataModel.getArticlesByTreeId(page = page, cid = cId)
            if (articleBeanRep.errorCode != -1) {
                if (page == 1) {
                    _articles.clear()
                }
                _articles.addAll(articleBeanRep.data!!.datas)
                when (topicScreenState.value.loadingState) {
                    LoadingState.Loading, LoadingState.LoadingMore -> {
                        if (_articles.isEmpty()) {
                            refreshViewState(LoadingState.Empty)
                        } else {
                            refreshViewState(LoadingState.LoadSuccess)
                        }
                    }
                    LoadingState.RefreshLoading -> refreshViewState(LoadingState.RefreshSuccess)
                    else -> {}
                }
            } else {
                refreshViewState(LoadingState.Error)
            }
        }
    }

    private fun refreshViewState(loadingState: LoadingState) {
        topicScreenState.value =
            TopicScreenState(_articles, loadingState)
    }
}