package com.naichuan.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naichuan.sunflower.data.UnsplashRepository

class GalleryViewModelFactory(
    private val repository: UnsplashRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(repository) as T
    }
}