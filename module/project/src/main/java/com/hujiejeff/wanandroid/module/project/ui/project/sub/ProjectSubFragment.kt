package com.hujiejeff.wanandroid.module.project.ui.project.sub

import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hujiejeff.wanadnroid.module.base.base.BaseMvvmFragment
import com.hujiejeff.wanadnroid.module.base.base.adapter.BaseAdapter
import com.hujiejeff.wanadnroid.module.base.base.adapter.BaseViewBindingHolder
import com.hujiejeff.wanadnroid.module.base.ext.loadUrl
import com.hujiejeff.wanandroid.module.common.databinding.ItemArticleBinding
import com.hujiejeff.wanandroid.module.project.R
import com.hujiejeff.wanandroid.module.project.databinding.ProjectFragmentProjectSubBinding
import com.hujiejeff.wanandroid.module.project.databinding.ProjectItemProjectBinding
import network.bean.ArticleBean
import network.bean.TreeBean

class ProjectSubFragment :
    BaseMvvmFragment<ProjectFragmentProjectSubBinding, ProjectSubViewModel>() {
    companion object {
        fun newInstance(treeBean: TreeBean): Fragment {
            val bundle = Bundle().apply {
                putParcelable("TREE_BEAN", treeBean)
            }
            return ProjectSubFragment().apply { arguments = bundle }
        }
    }
    private lateinit var treeBean: TreeBean

    override fun initData() {
        super.initData()
        treeBean = arguments?.get("TREE_BEAN") as TreeBean
    }

    override fun ProjectFragmentProjectSubBinding.initView() {
        recyclerView.run {
            adapter = Adapter().apply {
                setOnItemClickListener { adapter, view, position ->
                    jumpH5((adapter as Adapter).data[position].link)
                }
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }

        refreshLayout.setOnLoadMoreListener {
            mViewModel.loadMore()
        }
        refreshLayout.setOnRefreshListener {
            mViewModel.refresh()
        }

        mViewModel.cid = treeBean.id
        mViewModel.articles.observe(this@ProjectSubFragment) {
            (recyclerView.adapter as Adapter).setList(it)
            mBinding.refreshLayout.finishLoadMore()
            mBinding.refreshLayout.finishRefresh()
        }
    }

    private fun jumpH5(url: String) {
        val request = NavDeepLinkRequest.Builder
            .fromUri("wanandroid://webview?url=${url}".toUri())
            .build()
        findNavController().navigate(request)
    }


    inner class Adapter : BaseAdapter<ArticleBean, ProjectItemProjectBinding>(R.layout.project_item_project) {
        override fun convert(holder: BaseViewBindingHolder<ProjectItemProjectBinding>, item: ArticleBean) {
            holder.mBinding.run {
                iv.loadUrl(item.envelopePic)
                tvAuthor.text = item.author
                tvTitle.text = item.title
                tvContent.text = item.desc
                tvDate.text = item.niceDate

            }
        }
    }
}