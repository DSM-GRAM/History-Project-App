package com.gram.dim.Ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gram.dim.Connector.ApiClient
import com.gram.dim.Model.InfoOfPlaceModel
import com.gram.dim.R
import kotlinx.android.synthetic.main.activity_info_of_place.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class InfoOfPlaceActivity : AppCompatActivity(),View.OnClickListener,OnMapReadyCallback {
    lateinit var map : GoogleMap
    var location = ""
    var siteCode = ""
    var siteName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_of_place)

        val intent = intent
        location = when(intent.getStringExtra("location")){
            "bla" -> "러시아, 블라디보스톡"
            "usu" -> "러시아, 우수리스크"
            else -> "null"
        }
        siteCode = intent.getStringExtra("siteCode")
        siteName = intent.getStringExtra("siteName")

        // VR 이미지 좀 주세요 ㅠㅠㅠㅠ
//        info_of_place_panorama_img_expand.loadImageFromBitmap(BitmapFactory.decodeResource(resources, R.drawable.panorama_example),null)
        (supportFragmentManager.findFragmentById(R.id.info_of_place_map_map_expand) as SupportMapFragment).getMapAsync(this)

        getSiteLocation(siteCode)

        info_of_place_panorama_btn.setOnClickListener(this)
        info_of_place_map_btn.setOnClickListener(this)

        info_of_place_back_btn.setOnClickListener { finish() }

        info_of_place_home_fab.setOnClickListener {
            val intent = Intent(this@InfoOfPlaceActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getSiteLocation(siteCode: String) {
        ApiClient.api.getMapLocation(siteCode)
                .enqueue(object : Callback<InfoOfPlaceModel>{
                    override fun onResponse(call: Call<InfoOfPlaceModel>, response: Response<InfoOfPlaceModel>) {
                        if (response.code() == 200){
                            val markerOptions = MarkerOptions()
                                    .apply { position(LatLng(response.body()!!.lat,response.body()!!.lng))
                                        title(siteName)
                                        snippet(location)}

                            map.addMarker(markerOptions).showInfoWindow()

                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(response.body()!!.lat,response.body()!!.lng),16F))
                        } else{
                            Log.d("MAP","없는 유적지")
                        }
                    }

                    override fun onFailure(call: Call<InfoOfPlaceModel>, t: Throwable) {
                            Log.d("MAP","실패 ㅠㅠ")
                    }
                })
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

    override fun onMapReady(Gmap: GoogleMap?) {

        map = Gmap!!

    }
}