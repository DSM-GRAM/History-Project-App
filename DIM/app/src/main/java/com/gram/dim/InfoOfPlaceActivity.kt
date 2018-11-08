package com.gram.dim

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_info_of_place.*

class InfoOfPlaceActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_of_place)

        info_of_place_panorama_img_expand.loadImageFromBitmap(BitmapFactory.decodeResource(resources,R.drawable.panorama_example),null)

        info_of_place_panorama_btn.setOnClickListener(this)
        info_of_place_map_btn.setOnClickListener(this)

        info_of_place_back_btn.setOnClickListener { finish() }
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.info_of_place_panorama_btn){
            info_of_place_map_btn.setBackgroundResource(R.drawable.back_btn_not_expand_round_shape_placeinfo)
            info_of_place_map_map.collapse()
            info_of_place_panorama_btn.setBackgroundResource(R.drawable.back_btn_expand_round_shape_placeinfo)
            info_of_place_panorama_img.expand()
        } else {
            info_of_place_panorama_btn.setBackgroundResource(R.drawable.back_btn_not_expand_round_shape_placeinfo)
            info_of_place_panorama_img.collapse()
            info_of_place_map_btn.setBackgroundResource(R.drawable.back_btn_expand_round_shape_placeinfo)
            info_of_place_map_map.expand()
        }
    }
}