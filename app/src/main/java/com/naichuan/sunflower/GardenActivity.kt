package com.naichuan.sunflower

import android.widget.Toast
import com.naichuan.sunflower.databinding.ActivityGardenBinding

class GardenActivity : BaseActivity<ActivityGardenBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_garden
    }

    override fun initData() {
        Toast.makeText(this, "Haha", Toast.LENGTH_SHORT).show()
    }
}