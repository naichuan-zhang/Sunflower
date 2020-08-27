package com.naichuan.sunflower.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.naichuan.sunflower.data.AppDatabase
import com.naichuan.sunflower.data.PlantRepository
import com.naichuan.sunflower.viewmodels.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(
            AppDatabase.getInstance(context).plantDao()
        )
    }

    fun providePlantListViewModelFactory(fragment: Fragment): PlantListViewModelFactory {
        return PlantListViewModelFactory(getPlantRepository(fragment.requireContext()), fragment)
    }
}