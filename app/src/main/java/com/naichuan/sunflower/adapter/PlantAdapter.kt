package com.naichuan.sunflower.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naichuan.sunflower.HomeViewPagerFragmentDirections
import com.naichuan.sunflower.data.Plant
import com.naichuan.sunflower.databinding.ListItemPlantBinding

class PlantAdapter : ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(ListItemPlantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(
        private val binding: ListItemPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.clickListener = View.OnClickListener {
                binding.plant?.let { plant ->
                    navigateToPlant(plant, it)
                }
            }
        }

        fun bind(item: Plant) {
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }

        private fun navigateToPlant(plant: Plant, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionHomeViewPagerFragmentToPlantDetailFragment(plant.plantId)
            view.findNavController().navigate(direction)
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}