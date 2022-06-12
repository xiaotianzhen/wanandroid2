package com.yicooll.wanandroid2.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroid2.entity.UserInfo
import com.yicooll.wanandroid2.repository.Callback
import com.yicooll.wanandroid2.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel : ViewModel() {

    var loadingLiveData = MutableLiveData<Boolean>()
    var userInfoLiveData = MutableLiveData<UserInfo>()

    fun getUserInfo(useCache: Boolean, userId: Int) {
        loadingLiveData.value = true
        UserRepository.getInstance().getUserInfo(useCache, userId, object : Callback<UserInfo> {
            override fun onSuccess(data: UserInfo) {
                userInfoLiveData.postValue(data)
                loadingLiveData.postValue(false)
            }

            override fun onFail(msg: String) {
                userInfoLiveData.postValue(null)
                loadingLiveData.postValue(false)
            }
        })
    }

    fun launch(
        block: suspend () -> Unit,
        error: suspend (Throwable) -> Unit,
        complete: suspend () -> Unit
    ) {
        GlobalScope.launch(Dispatchers.IO) { //IO线程
            try {
                block()
            } catch (e: Exception) {
                error(e)
            } finally {
                complete()
            }
        }
    }



    //此处返回用LiveData,而不是MutableLiveData，禁止在view层修改数据
    fun getLoadingLiveData(): LiveData<Boolean> {
        return loadingLiveData
    }

    fun getUserInfoLiveData(): LiveData<UserInfo> {
        return userInfoLiveData
    }

}