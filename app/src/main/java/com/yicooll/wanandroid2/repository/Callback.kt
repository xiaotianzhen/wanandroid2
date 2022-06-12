package com.yicooll.wanandroid2.repository

interface Callback<T> {
    fun onSuccess(t: T)
    fun onFail(msg: String)
}