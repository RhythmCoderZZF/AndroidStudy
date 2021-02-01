package com.example.android_study.samples.gallery.model

import com.example.android_study.samples.gallery.net.PixabayService

/**
 * Author:create by RhythmCoder
 * Date:2020/8/21
 * Description:
 */
class GalleryRepository {
    suspend fun fetchData() = PixabayService.create().getGallery()
}