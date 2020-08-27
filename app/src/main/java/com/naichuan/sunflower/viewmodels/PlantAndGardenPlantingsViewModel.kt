package com.naichuan.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import com.naichuan.sunflower.data.GardenPlanting
import com.naichuan.sunflower.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

class PlantAndGardenPlantingsViewModel(plantAndGardenPlantings: PlantAndGardenPlantings) {

    private val plant = checkNotNull(plantAndGardenPlantings.plant)
    private val gardenPlanting: GardenPlanting = plantAndGardenPlantings.gardenPlantings[0]

    val waterDateString: String = dateFormat.format(gardenPlanting.lastWateringDate.time)
    val wateringInterval
        get() = plant.wateringInterval
    val imageUrl
        get() = plant.imageUrl
    val plantName
        get() = plant.name
    val plantDateString: String = dateFormat.format(gardenPlanting.plantDate.time)
    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}