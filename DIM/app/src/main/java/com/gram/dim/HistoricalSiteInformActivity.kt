package com.gram.dim

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_historical_site_inform.*

class HistoricalSiteInformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_site_inform)

        lateinit var historicalSiteInformDialog: HistoricalSiteInformShowImageDialog
        val changePwCancelClickListener = View.OnClickListener { historicalSiteInformDialog.dismiss() }

        val extraImagesItems = arrayListOf<HistoricalSiteInformExtraImagesItem>(
                HistoricalSiteInformExtraImagesItem("asd", "asdf", "adsf"),
                HistoricalSiteInformExtraImagesItem("asd", "asdf", "adsf"),
                HistoricalSiteInformExtraImagesItem("asd", "asdf", "adsf")
        )


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

        btn_historical_site_inform_toolbar_back.setOnClickListener { historicalSiteInformDialog.dismiss() }

    }
}
