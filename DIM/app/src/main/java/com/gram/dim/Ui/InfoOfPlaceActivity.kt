package com.gram.dim.Ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gram.dim.Connector.ApiClient
import com.gram.dim.Model.InfoOfPlaceModel
import com.gram.dim.Model.TestModel
import com.gram.dim.Model.VRImageModel
import com.gram.dim.R
import com.gram.dim.Util.QuizData
import com.gram.dim.Util.getBitmapFromUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info_of_place.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class InfoOfPlaceActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {
    lateinit var map: GoogleMap
    var location = ""
    var siteCode = ""
    var siteName = ""
    var imagePath = ""
    var VRImagePath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_of_place)

        location = when (intent.getStringExtra("location")) {
            "bla" -> "러시아, 블라디보스톡"
            "usu" -> "러시아, 우수리스크"
            else -> "null"
        }
        siteCode = intent.getStringExtra("siteCode")
        siteName = intent.getStringExtra("siteName")
        imagePath = intent.getStringExtra("imagePath")

        Glide.with(this@InfoOfPlaceActivity).load(imagePath).into(info_of_place_back_img)
        (supportFragmentManager.findFragmentById(R.id.info_of_place_map_map_expand) as SupportMapFragment).getMapAsync(this)

        getSiteLocation(siteCode)
        getVRImageUrl(siteCode)

        Log.d("VR",VRImagePath)


        info_of_place_panorama_btn.setOnClickListener(this)
        info_of_place_map_btn.setOnClickListener(this)

        info_of_place_go_test_fab.setOnClickListener { getQuiz(siteCode) }

        info_of_place_back_btn.setOnClickListener { finish() }

        info_of_place_home_fab.setOnClickListener {
            val intent = Intent(this@InfoOfPlaceActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getVRImageUrl(siteCode: String) {
        ApiClient.api.getVRImage(siteCode)
                .enqueue(object : Callback<VRImageModel> {
                    override fun onResponse(call: Call<VRImageModel>, response: Response<VRImageModel>) {
                        if (response.code() == 200) {

                                val thread = Thread(Runnable {
                                    if(getBitmapFromUrl(response.body()!!.imagePath) != null)
                                        info_of_place_panorama_img_expand.loadImageFromBitmap(getBitmapFromUrl(response.body()!!.imagePath),null)
                                })

                                thread.start()

                        } else {
                            info_of_place_vrimg_flag_tv.visibility = View.VISIBLE
                            Toast.makeText(baseContext, "VRImage가 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<VRImageModel>, t: Throwable) {
                        info_of_place_vrimg_flag_tv.visibility = View.VISIBLE
                        Log.d("VR", "실패 ㅠㅠ")
                    }
                })
    }


    fun getQuiz(area: String) {
        val quizData = QuizData

        ApiClient.api.getQuiz(area)
                .enqueue(object : Callback<TestModel> {
                    override fun onResponse(call: Call<TestModel>, response: Response<TestModel>) {
                        if (response.code() == 200) {
                            Log.d("response.body()", "${response.body()}")
                            if (response.body()!!.questionOX.get(0) != "") {
                                quizData.questionMultiple = response.body()!!.questionMultiple
                                quizData.answerMultiple = response.body()!!.answerMultiple
                                quizData.questionOX = response.body()!!.questionOX
                                quizData.answerOX = response.body()!!.answerOX
                                quizData.wordOfnumber = response.body()!!.wordOfnumber
                                quizData.wordList = response.body()!!.wordList
                                val intent = Intent(this@InfoOfPlaceActivity, OXTestActivity::class.java)
                                startActivity(intent)
                                Log.d("QUIZ", "success")
                            } else {
                                if (response.body()!!.questionMultiple.get(0) != "") {
                                    quizData.questionMultiple = response.body()!!.questionMultiple
                                    quizData.answerMultiple = response.body()!!.answerMultiple
                                    quizData.wordOfnumber = response.body()!!.wordOfnumber
                                    quizData.wordList = response.body()!!.wordList
                                    val intent = Intent(this@InfoOfPlaceActivity, DragTestActivity::class.java)
                                    startActivity(intent)
                                    Log.d("QUIZ", "success")

                                } else {
                                    Toast.makeText(this@InfoOfPlaceActivity, "이 지역은 퀴즈가 없습니다.", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<TestModel>, t: Throwable) {
                        Toast.makeText(this@InfoOfPlaceActivity, "퀴즈를 받지 못하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    fun getSiteLocation(siteCode: String) {
        ApiClient.api.getMapLocation(siteCode)
                .enqueue(object : Callback<InfoOfPlaceModel> {
                    override fun onResponse(call: Call<InfoOfPlaceModel>, response: Response<InfoOfPlaceModel>) {
                        if (response.code() == 200) {
                            val markerOptions = MarkerOptions()
                                    .apply {
                                        position(LatLng(response.body()!!.lat, response.body()!!.lng))
                                        title(siteName)
                                        snippet(location)
                                    }

                            map.addMarker(markerOptions).showInfoWindow()

                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(response.body()!!.lat, response.body()!!.lng), 16F))
                        } else {
                            Log.d("MAP", "없는 유적지")
                        }
                    }

                    override fun onFailure(call: Call<InfoOfPlaceModel>, t: Throwable) {
                        Log.d("MAP", "실패 ㅠㅠ")
                    }
                })
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.info_of_place_panorama_btn) {
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