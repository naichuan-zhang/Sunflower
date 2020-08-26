package com.naichuan.sunflower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding>: AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            layoutInflater, getLayoutId(), null, false)
        setContentView(binding.root)
        initData()
    }

    protected open fun initData() {
        // init some common configs
    }

    abstract fun getLayoutId(): Int
}
