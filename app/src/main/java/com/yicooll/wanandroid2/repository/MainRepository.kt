package com.yicooll.wanandroid2.repository
import com.yicooll.wanandroid2.net.Api

object MainRepository {

    suspend fun getBanner() = Api.get().getBanner()
    fun saveToLocal(){}

}