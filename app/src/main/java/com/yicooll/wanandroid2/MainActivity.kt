package com.yicooll.wanandroid2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.yicooll.wanandroid2.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val provider = ViewModelProvider(this)
        val viewModel = provider.get(MainViewModel::class.java)
        viewModel.mBannerLiveData.observe(this) {
            Log.d("yicooll", "onCreate: 获取banner 成功")
        }
        viewModel.getBanner()
    }
}