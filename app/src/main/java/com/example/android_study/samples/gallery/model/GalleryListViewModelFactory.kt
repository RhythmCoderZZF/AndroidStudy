package com.example.android_study.samples.gallery.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Author:create by RhythmCoder
 * Date:2020/8/21
 * Description:
 */
class GalleryListViewModelFactory(val galleryRepository: GalleryRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryListViewModel(galleryRepository) as T
    }
}