package com.naichuan.sunflower

import android.widget.Toast
import com.naichuan.sunflower.databinding.FragmentViewPagerBinding

class HomeViewPagerFragment : BaseFragment<FragmentViewPagerBinding>(R.layout.fragment_view_pager) {

    override fun initData() {
        Toast.makeText(context, "hahah", Toast.LENGTH_SHORT).show()
    }
}