package com.gram.dim

import android.annotation.TargetApi
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.content.ClipData
import android.os.Build
import android.util.Log
import android.view.DragEvent
import android.view.View.OnDragListener
import android.widget.LinearLayout
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_drag_test.*
import android.content.ClipDescription








class DragTestActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_test)

        setSupportActionBar(toolbar_dragtest)
        setSupportActionBar(toolbar_dragtest_space)

        first_word.setOnLongClickListener(OnLongClickListener())
        second_word.setOnLongClickListener(OnLongClickListener())
        third_word.setOnLongClickListener(OnLongClickListener())
        fourth_word.setOnLongClickListener(OnLongClickListener())
        fifth_word.setOnLongClickListener(OnLongClickListener())
        sixth_word.setOnLongClickListener(OnLongClickListener())
        
    }

    inner class OnLongClickListener: View.OnLongClickListener{
        @TargetApi(Build.VERSION_CODES.N)
        override fun onLongClick(v: View): Boolean {
            val item = ClipData.Item(v.getTag() as CharSequence)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(v.getTag().toString(), mimeTypes, item)
            val shadowBuilder = View.DragShadowBuilder(v)
            v.startDragAndDrop(data, shadowBuilder, v, 0)
            v.setVisibility(View.INVISIBLE)
            return true
        }
    }

    inner class DragListener : OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {
            when(event.action)  {
                DragEvent.ACTION_DRAG_STARTED  ->
                    Log.d("DragClickListener", "ACTION_DRAG_STARTED")
                DragEvent.ACTION_DRAG_ENTERED ->
                    Log.d("DragClickListener", "ACTION_DRAG_ENTERED")
                DragEvent.ACTION_DRAG_EXITED  ->
                    Log.d("DragClickListener", "ACTION_DRAG_EXITED")
                DragEvent.ACTION_DROP -> {
                    if (v == first_space) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE

                    } else if (v == second_space) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE

                    } else  if(v ==  third_space) {
                        val view = event.localState as View
                        val viewgroup = view.parent as ViewGroup
                        viewgroup.removeView(view)

                        val containView = v as LinearLayout
                        containView.addView(view)
                        view.visibility = View.VISIBLE
                    }
                    else {
                        val view = event.localState as View
                        view.visibility = View.VISIBLE
                    }
                }
                DragEvent.ACTION_DRAG_ENDED ->
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED")
            }
            return true
        }
    }
}