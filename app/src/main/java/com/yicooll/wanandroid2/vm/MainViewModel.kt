package com.yicooll.wanandroid2.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yicooll.wanandroid2.entity.Banner
import com.yicooll.wanandroid2.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val mBannerLiveData = MutableLiveData<List<Banner>>()

    fun getBanner() {
        viewModelScope.launch {
            val res = apiCall { MainRepository.getBanner() }
            if (res?.data != null && res.errorCode == 0) {
                mBannerLiveData.postValue(res.data)
                MainRepository.saveToLocal()
            } else {
                //报错
            }
        }
    }
}