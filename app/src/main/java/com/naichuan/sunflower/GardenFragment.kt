package com.naichuan.sunflower

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.naichuan.sunflower.adapter.GardenPlantingAdapter
import com.naichuan.sunflower.adapter.PLANT_LIST_PAGE_INDEX
import com.naichuan.sunflower.databinding.FragmentGardenBinding
import com.naichuan.sunflower.utils.InjectorUtils
import com.naichuan.sunflower.viewmodels.GardenPlantingListViewModel

class GardenFragment : BaseFragment<FragmentGardenBinding>(R.layout.fragment_garden) {

    private val viewModel: GardenPlantingListViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(this)
    }

    override fun initBinding(binding: FragmentGardenBinding): View? {
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        binding.addButton.setOnClickListener {
            navigateToPlantListPage()
        }
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem = PLANT_LIST_PAGE_INDEX
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            binding.hasPlantings = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }
}