package com.yicooll.wanandroid2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.lifecycle.ViewModelProvider
import com.yicooll.wanandroid2.databinding.ActivityUserBinding
import com.yicooll.wanandroid2.vm.UserViewModel

class UserActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserBinding
    var userViewModel: UserViewModel? = null
    var isResume = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityUserBinding.inflate(layoutInflater).also {
            binding = it
        }.root)
        val viewModelProvider = ViewModelProvider(this)
        userViewModel = viewModelProvider.get(UserViewModel::class.java)

        userViewModel?.getLoadingLiveData()?.observe(this) {
            if (it) {
                binding.imLoading.apply {
                    visibility = View.VISIBLE
                    val animation = RotateAnimation(0f, 360f)
                    animation.repeatMode = Animation.RESTART
                    setAnimation(animation)
                }
            } else {
                binding.imLoading.apply {
                    clearAnimation()
                    visibility = View.GONE
                }
            }
        }
        userViewModel?.getUserInfoLiveData()?.observe(this) {
            it?.apply {
                binding.nickName.text = name
            }
        }
        userViewModel?.getUserInfo(false, 101)
        binding.nickName.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isResume) {
            isResume = true
        } else {
            userViewModel?.getUserInfo(true, 101)
        }
    }
}