package com.example.android_study.samples.gallery.gallery2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.android_study.R
import com.example.android_study.samples.gallery.gallery2.adapter.GalleryList2Adapter
import com.example.android_study.samples.gallery.gallery2.adapter.GalleryViewPagerAdapter
import com.example.android_study.samples.gallery.model.Hit
import kotlinx.android.synthetic.main.fragment_gallery_view_pager.*

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryViewPagerFragment : Fragment() {
    private var list: List<Hit>? = null
    private var position: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = arguments?.getParcelableArrayList(GalleryList2Adapter.PHOTO_LIST)
        position = arguments?.getInt(GalleryList2Adapter.POSITION)!!

        GalleryViewPagerAdapter().apply {
            viewPager.adapter = this
            submitList(list)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                textView.text = "${position + 1}/${list?.size}"
            }
        })
        viewPager.setCurrentItem(position, false)


    }
}