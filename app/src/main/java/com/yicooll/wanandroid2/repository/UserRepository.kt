package com.yicooll.wanandroid2.repository

import com.yicooll.wanandroid2.entity.UserInfo
import java.lang.RuntimeException

class UserRepository private constructor() {
    private var flag = false

    init {
        if (!flag) {
            flag = true
        } else {
            throw RuntimeException("singleTon is being attacked")
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(): UserRepository {
            return UserRepositoryHolder.holder
        }
    }

    //静态内部类
    private object UserRepositoryHolder {
        val holder = UserRepository()
    }

    fun getUserInfo(useCache: Boolean, userId: Int, callback: Callback<UserInfo>) {
        Thread {
            if (useCache) {
                getUsersFromLocal(callback)
            } else {
                Thread.sleep(3000)
                callback.onSuccess(UserInfo("yicooll", 27))
                saveUserToLocal("http://www.baidu.com", UserInfo("yicooll", 27))
            }
        }.start()

    }

    //使用mmkv缓存key为url，值为用户信息
    fun saveUserToLocal(url: String, user: UserInfo) {

    }

    fun getUsersFromLocal(callback: Callback<UserInfo>) {
        callback.onSuccess(UserInfo("缓存 yicooll", 27))
    }
}
