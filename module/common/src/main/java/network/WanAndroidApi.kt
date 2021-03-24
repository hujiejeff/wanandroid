package network

import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import network.bean.*
import retrofit2.http.*

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

    /**
     * 导航
     */
    @GET("/navi/json")
    suspend fun getAllNaviArticles(): BaseBean<NaviBean>

    /**
     *  项目分类
     */
    @GET("/project/tree/json")
    suspend fun getProjectTree(): BaseBean<List<TreeBean>>

    /**
     * 某个分类下的项目
     */
    @GET("/project/list/1/json?cid=294")
    suspend fun getProjectsByCid(@Query("cid") cid: Int): BaseBean<PageBean<ArticleBean>>

    //问答




    //个人

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<UserBean>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): BaseBean<Any>

    /**
     * 退出登录
     */
    @GET("/user/logout/json")
    suspend fun logout()


    //收藏

    /**
     * 收藏文章列表
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int): BaseBean<PageBean<ArticleBean>>

    /**
     * 收藏站内文章
     */
    @POST("https://www.wanandroid.com/lg/collect/{id}/json")
    suspend fun collectInnerArticle(@Path("id") id: Int)

    /**
     * 收藏站外文章
     */
    @FormUrlEncoded
    @POST("/lg/collect/add/json")
    suspend fun collectOutArticle(@Field("title") title: String, @Field("author") author: String)

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun unCollectArticle()

}