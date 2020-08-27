package com.naichuan.sunflower

import android.view.View
import com.naichuan.sunflower.databinding.FragmentGardenBinding

class GardenFragment : BaseFragment<FragmentGardenBinding>(R.layout.fragment_garden) {

    override fun initBinding(binding: FragmentGardenBinding): View? {
        return binding.root
    }

    override fun initData() {

    }
}