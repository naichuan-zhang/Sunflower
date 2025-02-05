package com.naichuan.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.naichuan.sunflower.data.UnsplashPhoto
import com.naichuan.sunflower.data.UnsplashRepository
import kotlinx.coroutines.flow.Flow

class GalleryViewModel constructor(
    private val repository: UnsplashRepository
) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<UnsplashPhoto>>? = null

    fun searchPhotos(queryString: String): Flow<PagingData<UnsplashPhoto>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<UnsplashPhoto>> =
            repository.getSearchResultStream(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}