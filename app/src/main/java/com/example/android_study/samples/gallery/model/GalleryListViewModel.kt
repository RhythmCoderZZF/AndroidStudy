package com.example.android_study.samples.gallery.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Author:create by RhythmCoder
 * Date:2020/8/21
 * Description:画廊首页ViewModel
 *
 */
class GalleryListViewModel(private val galleryRepository: GalleryRepository) : ViewModel() {
    private val _galleryLiveData = MutableLiveData<List<Hit>>()
    val galleryLiveData: LiveData<List<Hit>>
        get() {
            return _galleryLiveData
        }

    fun fetchData() = viewModelScope.launch {
        val fetchData = galleryRepository.fetchData()
        _galleryLiveData.postValue(fetchData.hits)
    }
}