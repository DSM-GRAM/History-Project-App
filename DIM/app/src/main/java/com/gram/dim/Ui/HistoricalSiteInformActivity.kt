package com.gram.dim.Ui

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gram.dim.Model.HistoricalSiteInformModel
import com.gram.dim.Adapter.HistoricalSiteInformExtraImagesAdapter
import com.gram.dim.Connector.ApiClient
import com.gram.dim.Model.HistoricalSiteInformExtraImagesItem
import com.gram.dim.R
import com.gram.dim.Util.HistoricalSiteInformShowImageDialog
import com.gram.dim.Util.RecyclerItemClickListener
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import kotlinx.android.synthetic.main.activity_historical_site_inform.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoricalSiteInformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_site_inform)

        var location: String = intent.getStringExtra("location")
        var siteCode: String = intent.getStringExtra("siteCode")
        var siteName: String = intent.getStringExtra("siteName")

        text_historical_site_inform_toolbar_toolbar_name.text = siteName
        text_historical_site_inform_name.text = siteName

        lateinit var historicalSiteInformDialog: HistoricalSiteInformShowImageDialog
        val changePwCancelClickListener = View.OnClickListener { historicalSiteInformDialog.dismiss() }

        val extraImagesItems = arrayListOf<HistoricalSiteInformExtraImagesItem>()


        val historicalSiteInformExtraImagesAdapter = HistoricalSiteInformExtraImagesAdapter(this, extraImagesItems)
        recycler_historical_site_inform_extra_images.adapter = historicalSiteInformExtraImagesAdapter

        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_historical_site_inform_extra_images.layoutManager = lm
        recycler_historical_site_inform_extra_images.setHasFixedSize(true)

        recycler_historical_site_inform_extra_images.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_historical_site_inform_extra_images, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                historicalSiteInformDialog = HistoricalSiteInformShowImageDialog(this@HistoricalSiteInformActivity, changePwCancelClickListener, extraImagesItems[position].imagePath)
                historicalSiteInformDialog.show()
            }

            override fun onLongItemClick(view: View?, position: Int) {

            }

        }))

        //오른쪽 화살표 아이콘 못구해서 가로로 뒤집음ㅎㅎ
        btn_historical_site_inform_next.rotationY = 180f

        btn_historical_site_inform_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_historical_site_inform_next.setOnClickListener {
            val intent = Intent(this, InfoOfPlaceActivity::class.java)
            intent.putExtra("location", location)
            intent.putExtra("siteCode", siteCode)
            startActivity(intent)
        }


        btn_historical_site_inform_toolbar_back.setOnClickListener { finish() }

        ApiClient.api.getSiteInform(location, siteCode).enqueue(object : Callback<HistoricalSiteInformModel> {

            override fun onResponse(call: Call<HistoricalSiteInformModel>, response: Response<HistoricalSiteInformModel>) {
                if (response.code() == 200) {
                    text_historical_site_inform_location.text = response.body()!!.location
                    text_historical_site_inform_main_text.text = response.body()!!.text
                    image_historical_site_inform_card.maxHeight = text_historical_site_inform_extra_text.height
                    text_historical_site_inform_extra_text.text = response.body()!!.extraText
                    text_historical_site_inform_explain.text = response.body()!!.explain
                    Glide.with(this@HistoricalSiteInformActivity).load(response.body()!!.imagePath).into(image_historical_site_inform_toolbar)
                    Glide.with(this@HistoricalSiteInformActivity).load(response.body()!!.imagePath).into(image_historical_site_inform_card)
                    for (i in response.body()!!.extra.indices) {
                        extraImagesItems.add(HistoricalSiteInformExtraImagesItem(response.body()!!.extra[i].extraImagePath, response.body()!!.extra[i].extraName, response.body()!!.extra[i].extraLocation))
                    }

                    historicalSiteInformExtraImagesAdapter.notifyDataSetChanged()

                } else {
                    Log.d("Debug", "ㅠ")
                    Toast.makeText(applicationContext, "오류", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<HistoricalSiteInformModel>, t: Throwable) {
                Log.d("Debug", "ㅜㅜㅜㅜㅜ")
                Toast.makeText(applicationContext, "오류", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun resize() {

    }
}
