package com.naichuan.sunflower.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.naichuan.sunflower.data.Plant
import com.naichuan.sunflower.data.PlantRepository

class PlantListViewModel constructor(
    plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val plants: LiveData<List<Plant>> = getSavedGrowZoneNumber().switchMap { num ->
        if (num == NO_GROW_ZONE)
            plantRepository.getPlants()
        else
            plantRepository.getPlantsWithGrowZoneNumber(num)
    }

    fun setGrowZoneNumber(num: Int) {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, num)
    }

    fun clearGrowZoneNumber() {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE)
    }

    fun isFiltered() = getSavedGrowZoneNumber().value != NO_GROW_ZONE

    private fun getSavedGrowZoneNumber(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE)
    }

    companion object {
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }
}