package com.naichuan.sunflower

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.naichuan.sunflower.adapter.PlantAdapter
import com.naichuan.sunflower.databinding.FragmentPlantListBinding
import com.naichuan.sunflower.utils.InjectorUtils
import com.naichuan.sunflower.viewmodels.PlantListViewModel

class PlantListFragment : BaseFragment<FragmentPlantListBinding>(R.layout.fragment_plant_list) {

    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(this)
    }

    override fun initBinding(binding: FragmentPlantListBinding): View? {
        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered())
                clearGrowZoneNumber()
            else
                setGrowZoneNumber(9)
        }
    }

    companion object {
        private const val TAG = "PlantListFragment"
    }
}