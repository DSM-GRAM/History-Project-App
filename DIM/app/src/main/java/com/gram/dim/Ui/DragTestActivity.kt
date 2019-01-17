package com.gram.dim.Ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.content.ClipData
import android.util.Log
import android.view.DragEvent
import android.view.View.OnDragListener
import kotlinx.android.synthetic.main.activity_drag_test.*
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.widget.TextView
import com.gram.dim.R
import com.gram.dim.Util.TestModel


class DragTestActivity: AppCompatActivity(){
    lateinit var testModel: TestModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_test)

        testModel = TestModel

        setSupportActionBar(toolbar_dragtest)
        setSupportActionBar(toolbar_dragtest_space)

        first_space.setOnDragListener(DragListener())
        second_space.setOnDragListener(DragListener())
        third_space.setOnDragListener(DragListener())
        layout.setOnDragListener(DragListener())

        arrow_back.setOnClickListener { v ->
            finish()
        }


    }

    fun WordDrag(v: View){
        v.setOnTouchListener(OnTouchListener())
    }



    inner class OnTouchListener: View.OnTouchListener{
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            if (event.action == ACTION_DOWN){
                val data = ClipData.newPlainText("dragtext", "dragtext")
                val shadowBuilder = View.DragShadowBuilder(v)
                v.startDrag(data, shadowBuilder, v, 0)
                v.setVisibility(View.INVISIBLE)
                return true
            }
            return false
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
                    if (v == first_space || v == second_space || v == third_space) {
                        val view: View = event.localState as View
                        view.visibility = View.INVISIBLE

                        val dragedView = view as TextView
                        val containView = v as TextView
                        containView.setText(dragedView.text)
                        view.visibility = View.VISIBLE

                    } else if(v == layout) {
                        val view: View = event.localState as View
                        view.visibility = View.VISIBLE
                    } else {
                        val view: View = event.localState as View
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