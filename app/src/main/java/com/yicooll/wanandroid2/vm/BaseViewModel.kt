package com.yicooll.wanandroid2.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroid2.entity.ResponseResult
import com.yicooll.wanandroid2.net.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {

    suspend inline fun <T> apiCall(crossinline call: suspend CoroutineScope.() -> ResponseResult<T>): ResponseResult<T> {
        return withContext(Dispatchers.IO) {
            val res: ResponseResult<T>
            try {
                res = call()
            } catch (e: Throwable) {
                Log.e("ApiCaller", "request error", e)
                // 请求出错，将状态码和消息封装为 ResponseResult
                return@withContext ApiException.build(e).toResponse<T>()
            }
            if (res.errorCode == ApiException.CODE_AUTH_INVALID) {
                Log.e("ApiCaller", "request auth invalid")
                // 登录过期，取消协程，跳转登录界面
                // 省略部分代码
                cancel()
            }
            return@withContext res
        }
    }
}