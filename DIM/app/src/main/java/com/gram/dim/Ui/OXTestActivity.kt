package com.gram.dim.Ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gram.dim.R
import kotlinx.android.synthetic.main.activity_o_x.*


class OXTestActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_x)

        setSupportActionBar(toolbar_oxtest)
        arrow_back.setOnClickListener { v ->
            finish()
        }
        btn_o.setOnClickListener { v ->
            v.visibility = View.GONE
        }
        btn_x.setOnClickListener { v ->
            v.visibility = View.GONE
        }
    }
}