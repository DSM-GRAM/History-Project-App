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

    var answerOfnumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_test)

        quiz_drag_question_tv.text = quizData.questionMultiple.get(0)
        quizData.questionMultiple.removeAt(0)

        answer = quizData.answerMultiple.get(0)
        quizData.answerMultiple.removeAt(0)

        answerOfnumber = quizData.answerOfnumber.get(0)
        quizData.answerOfnumber.removeAt(0)
        when(answerOfnumber){
            1 -> {
                second_space.visibility = View.GONE
                third_space.visibility = View.GONE
            }
            2 -> {
                third_space.visibility = View.GONE
            }
        }

        first_word.text = quizData.wordList.get(0).word1
        second_word.text = quizData.wordList.get(0).word2
        third_word.text = quizData.wordList.get(0).word3
        fourth_word.text = quizData.wordList.get(0).word4
        fifth_word.text = quizData.wordList.get(0).word5
        sixth_word.text = quizData.wordList.get(0).word6
        quizData.wordList.removeAt(0)

        setSupportActionBar(toolbar_dragtest)
        setSupportActionBar(toolbar_dragtest_space)

        first_space.setOnDragListener(DragListener())
        second_space.setOnDragListener(DragListener())
        third_space.setOnDragListener(DragListener())
        quiz_drag_lay.setOnDragListener(DragListener())

        quiz_drag_arrow_back_imgv.setOnClickListener { finish() }

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
            when(answerOfnumber){
                1 -> {
                    if (first_space.text != "(1)"){
                        if(first_space.text == answer){
                            Toast.makeText(this@DragTestActivity, "정답입니다!", Toast.LENGTH_SHORT).show()
                            checkQuestion()
                        } else {
                            Toast.makeText(this@DragTestActivity, "오답입니다!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                2 -> {
                    if (first_space.text != "(1)" && second_space.text != "(2)"){
                        Toast.makeText(this@DragTestActivity, "정답입니다!", Toast.LENGTH_SHORT).show()
                        checkQuestion()
                    } else {
                        Toast.makeText(this@DragTestActivity, "오답입니다!", Toast.LENGTH_SHORT).show()
                    }
                }
                3 -> {
                    if (first_space.text != "(1)" && second_space.text != "(2)" && third_space.text != "(3)"){
                        Toast.makeText(this@DragTestActivity, "정답입니다!", Toast.LENGTH_SHORT).show()
                        checkQuestion()
                    } else {
                        Toast.makeText(this@DragTestActivity, "오답입니다!", Toast.LENGTH_SHORT).show()
                    }
                }
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