package com.yicooll.wanandroid2.net

import com.yicooll.wanandroid2.entity.Banner
import com.yicooll.wanandroid2.entity.ResponseResult
import retrofit2.http.GET

/**
 * 创建 Retrofit Api 接口，Retrofit 自 2.6 版本开始，原生支持了协程，
 * 我们只需要在方法前添加 suspend 修饰符，即可直接返回实体对象
 */
interface IApi {
    @GET("banner/json222")
    suspend fun getBanner(): ResponseResult<List<Banner>>
}