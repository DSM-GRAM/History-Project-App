package com.gram.dim

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_historical_site_inform.*

class HistoricalSiteInformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_site_inform)

        val extraImagesItems = arrayListOf<HistoricalSiteInformExtraImagesItem>(
                HistoricalSiteInformExtraImagesItem("asd","asdf","adsf"),
                HistoricalSiteInformExtraImagesItem("asd","asdf","adsf"),
                HistoricalSiteInformExtraImagesItem("asd","asdf","adsf")
        )


        val historicalSiteInformExtraImagesAdapter = HistoricalSiteInformExtraImagesAdapter(this, extraImagesItems)
        recycler_historical_site_inform_extra_images.adapter = historicalSiteInformExtraImagesAdapter

        val lm = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycler_historical_site_inform_extra_images.layoutManager = lm
        recycler_historical_site_inform_extra_images.setHasFixedSize(true)

        btn_historical_site_inform_toolbar_back.setOnClickListener { finish() }
    }
}
