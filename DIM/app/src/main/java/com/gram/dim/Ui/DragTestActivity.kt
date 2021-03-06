package com.gram.dim.Ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.content.ClipData
import android.content.Intent
import android.util.Log
import android.view.DragEvent
import android.view.View.OnDragListener
import kotlinx.android.synthetic.main.activity_drag_test.*
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.widget.TextView
import android.widget.Toast
import com.gram.dim.Model.TestModel
import com.gram.dim.R
import com.gram.dim.Util.QuizData


class DragTestActivity: AppCompatActivity(){

    val quizData = QuizData

    var answer = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_test)

        quiz_drag_question_tv.text = quizData.questionMultiple.get(0)
        quizData.questionMultiple.removeAt(0)

        answer = quizData.answerMultiple.get(0)
        quizData.answerMultiple.removeAt(0)

        when(quizData.wordOfnumber.get(0)){
            3 -> {
                first_word.text = quizData.wordList.get(0).get(0)
                second_word.text = quizData.wordList.get(0).get(1)
                third_word.text = quizData.wordList.get(0).get(2)
                fourth_word.visibility = View.GONE
            }

            4 -> {
                first_word.text = quizData.wordList.get(0).get(0)
                second_word.text = quizData.wordList.get(0).get(1)
                third_word.text = quizData.wordList.get(0).get(2)
                fourth_word.text = quizData.wordList.get(0).get(3)
            }
        }
        quizData.wordOfnumber.removeAt(0)
        quizData.wordList.removeAt(0)

        setSupportActionBar(toolbar_dragtest)
        setSupportActionBar(toolbar_dragtest_space)

        first_word.setOnTouchListener(OnTouchListener())
        second_word.setOnTouchListener(OnTouchListener())
        third_word.setOnTouchListener(OnTouchListener())
        fourth_word.setOnTouchListener(OnTouchListener())

        first_space.setOnDragListener(DragListener())
        quiz_drag_lay.setOnDragListener(DragListener())

        quiz_drag_arrow_back_imgv.setOnClickListener { finish() }

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
                    if (v == first_space) {
                        val view: View = event.localState as View
                        view.visibility = View.INVISIBLE

                        val dragedView = view as TextView
                        val containView = v as TextView
                        containView.setText(dragedView.text)
                        view.visibility = View.VISIBLE
                        checkAnswer()

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

        fun checkAnswer(){
            if(first_space.text == answer){
                Toast.makeText(this@DragTestActivity, "정답입니다!", Toast.LENGTH_SHORT).show()
                checkQuestion()
            } else {
                Toast.makeText(this@DragTestActivity, "오답입니다!", Toast.LENGTH_SHORT).show()
            }
        }

        fun checkQuestion(){
            if (quizData.questionMultiple.isNotEmpty()){
                var intent = Intent(this@DragTestActivity, DragTestActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }
}