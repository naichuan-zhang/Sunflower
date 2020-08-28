package com.naichuan.sunflower

import android.app.Application
import com.naichuan.sunflower.utils.ACCESS_KEY
import com.naichuan.sunflower.utils.SECRET_KEY
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UnsplashPhotoPicker.init(
            this,
            ACCESS_KEY,
            SECRET_KEY
        )
    }
}