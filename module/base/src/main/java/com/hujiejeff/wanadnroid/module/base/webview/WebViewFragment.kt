package com.hujiejeff.wanadnroid.module.base.webview

import android.R
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hujiejeff.wanadnroid.module.base.base.BaseFragment
import com.hujiejeff.wanadnroid.module.base.databinding.FragmentWebviewBinding
import com.just.agentweb.*
import kotlinx.android.synthetic.main.fragment_webview.*


class WebViewFragment: BaseFragment<FragmentWebviewBinding>() {
    private lateinit var currentUrl: String
    private val agentWeb by lazy {
        AgentWeb.with(this)
            .setAgentWebParent(
                mBinding.webView.parent as ViewGroup,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            .useDefaultIndicator(R.color.holo_purple)
            .setWebViewClient(object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    currentUrl = url.orEmpty()
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    if (!request?.url.toString().startsWith("http")) {
                        return true
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }
            })
            .setWebChromeClient(object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    mBinding.toolbar.title = title
                }
            })
            .createAgentWeb()
            .ready()
            .get()
    }

    override fun FragmentWebviewBinding.initView() {
        currentUrl = arguments?.getString("url") ?: ""
//        (activity as AppCompatActivity).setSupportActionBar(mBinding.toolbar)
        mBinding.webView.loadUrl(currentUrl)
//        agentWeb.urlLoader.loadUrl(currentUrl)
    }
}