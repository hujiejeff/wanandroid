package com.hujiejeff.wanandroi.module.main

import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.hujiejeff.wanadnroid.module.base.base.BaseMvvmFragment
import com.hujiejeff.wanadnroid.module.base.base.adapter.BaseAdapter
import com.hujiejeff.wanadnroid.module.base.base.adapter.BaseViewBindingHolder
import com.hujiejeff.wanadnroid.module.base.constans.RouteMap
import com.hujiejeff.wanadnroid.module.base.ext.loadUrl
import com.hujiejeff.wanandroi.module.main.databinding.MainFragmentHomeBinding
import com.hujiejeff.wanandroi.module.main.databinding.MainIncludeFragmentHomeHeaderBinding
import com.hujiejeff.wanandroid.module.common.databinding.ItemArticleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import network.bean.ArticleBean
import network.bean.BannerBean

@Route(path = RouteMap.Main.HOME_FRAGMENT)
class MainFragment : BaseMvvmFragment<MainFragmentHomeBinding, MainViewModel>() {
    private val mHeaderBinding by lazy {
        MainIncludeFragmentHomeHeaderBinding.inflate(layoutInflater)
    }
    override fun MainFragmentHomeBinding.initView() {
        recyclerView.run {
            adapter = Adapter().apply {
                addHeaderView(mHeaderBinding.root)
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        val adapter: BGABanner.Adapter<ImageView, BannerBean>  = BGABanner.Adapter { _, image, model, _ ->
            image.loadUrl(model!!.imagePath)
        }
        mHeaderBinding.banner.setAdapter(adapter)
    }

    override fun initData() {
        super.initData()
        mViewModel.requestData()
        mViewModel.banner.observe(this@MainFragment) {
            mHeaderBinding.banner.setData(it, null)
        }
    }

    override suspend fun MainViewModel.subscribe() {
        dataState.collect {
            (mBinding.recyclerView.adapter as Adapter).setList(it.articles)
//            mHeaderBinding.banner.setData(it.banner, null) //todo banner有问题
        }
    }

    inner class Adapter : BaseAdapter<ArticleBean, ItemArticleBinding>(R.layout.item_article) {
        override fun convert(holder: BaseViewBindingHolder<ItemArticleBinding>, item: ArticleBean) {
            holder.mBinding.run {
                tvAuthor.text = item.author
                tvTitle.text = item.title
                tvDate.text = item.niceDate
                tvCategory.text = "${item.superChapterName}/${item.chapterName}"
//                tvTag.text = item.tags[0].name
            }
        }
    }
}