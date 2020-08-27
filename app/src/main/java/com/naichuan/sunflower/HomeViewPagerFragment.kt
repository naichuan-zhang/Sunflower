package com.naichuan.sunflower

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.naichuan.sunflower.adapter.MY_GARDEN_PAGE_INDEX
import com.naichuan.sunflower.adapter.PLANT_LIST_PAGE_INDEX
import com.naichuan.sunflower.adapter.SunflowerPagerAdapter
import com.naichuan.sunflower.databinding.FragmentViewPagerBinding
import java.lang.IndexOutOfBoundsException

class HomeViewPagerFragment : BaseFragment<FragmentViewPagerBinding>(R.layout.fragment_view_pager) {

    override fun initBinding(binding: FragmentViewPagerBinding): View? {
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = SunflowerPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.setIcon(getTabIcon(position))
                tab.text = getTabTitle(position)
            }
        ).attach()
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> null
        }
    }
}