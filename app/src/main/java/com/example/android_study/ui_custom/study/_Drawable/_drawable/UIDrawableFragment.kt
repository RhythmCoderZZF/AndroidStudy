package com.example.android_study.ui_custom.study._Drawable._drawable

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_u_i_cus_drawable_drawable.*

class UIDrawableFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_drawable_drawable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv.setImageDrawable(VRoundDrawable(BitmapFactory.decodeResource(resources, R.mipmap.ic_cmd)))
        ivCenter.apply {
            setImageDrawable(VRoundDrawable(BitmapFactory.decodeResource(resources, R.mipmap.ic_cmd)))
            scaleType = ImageView.ScaleType.CENTER
            post {
                CmdUtil.v("${iv.drawable.bounds}")
                CmdUtil.v("${ivCenter.drawable.bounds}")
            }
        }

        ivFitXY.apply {
            setImageDrawable(VRoundDrawable(BitmapFactory.decodeResource(resources, R.mipmap.ic_cmd)))
            scaleType = ImageView.ScaleType.FIT_XY
        }

        ivCenterCrop.apply {
            setImageDrawable(VRoundDrawable(BitmapFactory.decodeResource(resources, R.mipmap.ic_cmd)))
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

}