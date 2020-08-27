package com.naichuan.sunflower.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naichuan.sunflower.HomeViewPagerFragmentDirections
import com.naichuan.sunflower.R
import com.naichuan.sunflower.data.PlantAndGardenPlantings
import com.naichuan.sunflower.databinding.ListItemGardenPlantingBinding
import com.naichuan.sunflower.viewmodels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter : ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(GardenPlantingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_garden_planting,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemGardenPlantingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.clickListener = View.OnClickListener {
                binding.viewModel?.plantId?.let { plantId ->
                    navigateToPlant(plantId, it)
                }
            }
        }

        private fun navigateToPlant(plantId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionHomeViewPagerFragmentToPlantDetailFragment(plantId)
            view.findNavController().navigate(direction)
        }

        fun bind(plantAndGardenPlantings: PlantAndGardenPlantings) {
            with(binding) {
                viewModel = PlantAndGardenPlantingsViewModel(plantAndGardenPlantings)
                executePendingBindings()
            }
        }
    }
}

private class GardenPlantingDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
    override fun areItemsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant == newItem.plant
    }
}