package com.naichuan.sunflower.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.naichuan.sunflower.api.UnsplashService
import com.naichuan.sunflower.data.AppDatabase
import com.naichuan.sunflower.data.GardenPlantingRepository
import com.naichuan.sunflower.data.PlantRepository
import com.naichuan.sunflower.data.UnsplashRepository
import com.naichuan.sunflower.viewmodels.GalleryViewModelFactory
import com.naichuan.sunflower.viewmodels.GardenPlantingListViewModelFactory
import com.naichuan.sunflower.viewmodels.PlantDetailViewModelFactory
import com.naichuan.sunflower.viewmodels.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(
            AppDatabase.getInstance(context).plantDao()
        )
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context).gardenPlantingDao()
        )
    }

    fun providePlantListViewModelFactory(fragment: Fragment): PlantListViewModelFactory {
        return PlantListViewModelFactory(getPlantRepository(fragment.requireContext()), fragment)
    }

    fun provideGardenPlantingListViewModelFactory(fragment: Fragment): GardenPlantingListViewModelFactory {
        return GardenPlantingListViewModelFactory(getGardenPlantingRepository(fragment.requireContext()), fragment)
    }

    fun providePlantDetailViewModelFactory(fragment: Fragment, plantId: String): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(fragment.requireContext()), getGardenPlantingRepository(fragment.requireContext()), plantId, fragment)
    }
    
    fun provideGalleryViewModelFactory(): GalleryViewModelFactory {
        return GalleryViewModelFactory(
            UnsplashRepository(UnsplashService.create())
        )
    }
}