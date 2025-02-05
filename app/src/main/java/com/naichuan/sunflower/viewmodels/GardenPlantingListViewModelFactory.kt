package com.naichuan.sunflower.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.naichuan.sunflower.data.GardenPlantingRepository

class GardenPlantingListViewModelFactory(
    private val gardenPlantingRepository: GardenPlantingRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return GardenPlantingListViewModel(gardenPlantingRepository) as T
    }
}