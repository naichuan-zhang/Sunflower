package com.naichuan.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naichuan.sunflower.BuildConfig
import com.naichuan.sunflower.data.GardenPlantingRepository
import com.naichuan.sunflower.data.PlantRepository
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    val isPlanted = gardenPlantingRepository.isPlanted(plantId)
    val plant = plantRepository.getPlant(plantId)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

    fun hasValidUnSplashKey() = null
}