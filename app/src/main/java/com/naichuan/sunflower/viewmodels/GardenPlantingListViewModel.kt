package com.naichuan.sunflower.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naichuan.sunflower.data.GardenPlantingRepository
import com.naichuan.sunflower.data.PlantAndGardenPlantings

class GardenPlantingListViewModel constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>>
            = gardenPlantingRepository.getPlantedGardens()
}