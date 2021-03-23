package network

import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import network.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidApi {
    //首页

    /**
     * 首页文章
     */
    @GET("/article/list/{page}/json")
    suspend fun getMainArticles(@Path("page") page: Int): BaseBean<PageBean<ArticleBean>>

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getBanners(): BaseBean<List<BannerBean>>

    /**
     * 常用网站
     */
    @GET("/friend/json")
    suspend fun getCommonlyUsedSites(): BaseBean<List<CommonlyUsedSiteBean>>

    /**
     * 搜索热词
     */
    @GET("/hotkey/json")
    suspend fun getSearchHotKeys(): BaseBean<List<SearchHotKeyBean>>


    /**
     * 置顶文章
     */
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseBean<List<ArticleBean>>

    //体系

    /**
     * 体系
     */
    @GET("/tree/json")
    suspend fun getDataTrees(): BaseBean<List<TreeBean>>

    /**
     * 获取体系下面的文章
     */
    @GET("/article/list/{page}/json?cid=60")
    suspend fun getArticlesByTreeId(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseBean<PageBean<ArticleBean>>

    /**
     * 搜索文章通过作者昵称
     */
    @GET("/article/list/0/json?author=鸿洋")
    suspend fun searchArticlesByAuthor(): BaseBean<PageBean<ArticleBean>>

//    //导航
//    @GET("/navi/json")
//    suspend fun
    //问答


    //个人
}