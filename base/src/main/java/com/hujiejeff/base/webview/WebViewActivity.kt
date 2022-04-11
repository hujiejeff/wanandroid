package com.hujiejeff.base.webview

import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.hujiejeff.base.R
import com.hujiejeff.base.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    companion object {
        const val KEY_WEB_VIEW_PATH: String = "path"
        const val KEY_WEB_VIEW_TITLE: String = "title"
    }

    private lateinit var mBinding: ActivityWebViewBinding
    private lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWebViewBinding.inflate(layoutInflater)
        val view = mBinding.root
        window.statusBarColor = resources.getColor(R.color.purple_700)
        setContentView(view)
        initData(savedInstanceState)
    }

    fun initData(savedInstanceState: Bundle?) {
        val path = intent.extras?.getString(KEY_WEB_VIEW_PATH)
        val title = intent.extras?.getString(KEY_WEB_VIEW_TITLE)
        initToolbar(title)
        initWebView(path)
    }

    private fun initWebView(path: String?) {
        mWebView = WebView(this)
        val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        mWebView.layoutParams = layoutParams
        mBinding.flWebviewContain.addView(mWebView)

        val webSetting = mWebView.settings
        webSetting.domStorageEnabled = true
        webSetting.useWideViewPort = true
        webSetting.loadWithOverviewMode = true
        webSetting.javaScriptEnabled = true

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url == null) {
                    return false
                }
                try {
                    if (url.startsWith("weixin://") || url.startsWith("jianshu://")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                } catch (e: Exception) {
                    return true
                }
                view?.loadUrl(url)
                return true
            }
        }

        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                mBinding.progressBar.isVisible = newProgress != 100
                mBinding.progressBar.progress = newProgress
            }
        }

        mWebView.loadUrl(path ?: "")
    }

    override fun onDestroy() {
        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        mWebView.clearHistory()
        (mWebView.parent as ViewGroup).removeView(mWebView)
        mWebView.destroy()
        super.onDestroy()
    }

    private fun initToolbar(title: String?) {
        mBinding.toolbarLayout.run {
            setNavigationIcon(R.drawable.ic_back)
            setTitle(title)
        }
        setSupportActionBar(mBinding.toolbarLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}