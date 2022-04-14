package com.hujiejeff.wanandroid_compose.network

import com.hujiejeff.base.data.remote.bean.BaseBean
import com.hujiejeff.wanandroid_compose.network.bean.*
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
    @GET("/article/list/{page}/json")
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
    @GET("/project/list/{page}/json")
    suspend fun getProjectsByCid(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseBean<PageBean<ArticleBean>>

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
    suspend fun unCollectArticle(@Path("id") id: Int)

    /**
     * 我的收藏中取消收藏
     */
    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    suspend fun unCollectArticleInMyCollect(@Path("id") id: Int, @Field("originId") originId: Int)


    /**
     * 收藏网址l列表
     */
    @GET("/lg/collect/usertools/json")
    suspend fun myCollectSites()

    /**
     * 收藏网址
     */
    @POST("/lg/collect/addtool/json")
    @FormUrlEncoded
    suspend fun collectSite(
        @Field("name") name: String,
        @Field("link") link: String
    )

    /**
     * 编辑收藏网址
     */
    @POST("/lg/collect/updatetool/json")
    @FormUrlEncoded
    suspend fun collectSite(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("link") link: String
    )

    /**
     * 删除收藏网址
     */
    @POST("lg/collect/deletetool/json")
    @FormUrlEncoded
    suspend fun deleteCollectSite(@Field("id") id: Int)

    /**
     * 搜索
     */

    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    suspend fun search(
        @Path("page") page: Int,
        @Field("k") key: String
    ): BaseBean<PageBean<ArticleBean>>

    //积分


    //广场
}