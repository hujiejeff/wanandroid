package com.hujiejeff.wanandroid.module.project.ui.project.sub

import android.util.Log
import androidx.lifecycle.*
import com.hujiejeff.wanadnroid.module.base.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import network.DataModel
import network.bean.ArticleBean

class ProjectSubViewModel: BaseViewModel() {
    private val page = MutableLiveData(1)
    var cid: Int = -1
    private val _articles: MutableList<ArticleBean> = mutableListOf()
    val articles: LiveData<List<ArticleBean>> = page.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val rep = DataModel.getProjectsByCid(page = it, cid = cid)
            if (page.value == 1) {
                _articles.clear()
                _articles.addAll(rep.data?.datas!!)
                emit(_articles)
            } else {
                _articles.addAll(rep.data?.datas!!)
                emit(_articles)
            }
        }
    }

    override fun refresh() {
        super.refresh()
        page.value = 1

    }

    override fun loadMore() {
        super.loadMore()
        page.value = page.value?.plus(1)
    }
}