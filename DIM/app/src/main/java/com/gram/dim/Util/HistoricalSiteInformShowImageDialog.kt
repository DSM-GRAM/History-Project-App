package com.gram.dim.Util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.gram.dim.R
import kotlinx.android.synthetic.main.dialog_historical_site_inform_show_image.*

class HistoricalSiteInformShowImageDialog(context: Context, var cancelClickLister: View.OnClickListener, var imagePath: String) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow
        setContentView(R.layout.dialog_historical_site_inform_show_image)
        if (btn_dialog_historical_site_inform_show_image_cancel != null) {
            btn_dialog_historical_site_inform_show_image_cancel.setOnClickListener(cancelClickLister)
        }

        Glide.with(context).load(imagePath).into(image_dialog_historical_site_inform_show_image)
    }
}