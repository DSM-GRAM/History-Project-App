package com.gram.dim.Ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.gram.dim.R
import com.gram.dim.Util.QuizData
import kotlinx.android.synthetic.main.activity_o_x.*


class OXTestActivity : AppCompatActivity(){

    val quizData = QuizData

    var answer = ""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_x)

        quiz_ox_question_tv.text = quizData.questionOX.get(0)
        quizData.questionOX.removeAt(0)

        answer = quizData.answerOX.get(0)
        quizData.answerOX.removeAt(0)


        setSupportActionBar(toolbar_oxtest)
        quiz_ox_arrow_back_imgv.setOnClickListener { finish() }
        btn_o.setOnClickListener { v ->
            v.visibility = View.GONE
            Thread.sleep(500)
            v.visibility = View.VISIBLE
            checkAnswer("O")
        }
        btn_x.setOnClickListener { v ->
            v.visibility = View.GONE
            Thread.sleep(500)
            v.visibility = View.VISIBLE
            checkAnswer("X")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkAnswer(answer: String){
        if(this.answer == answer){
            quiz_ox_lay.setBackgroundColor(Color.parseColor("#3dcf78"))
            quiz_ox_arrow_back_imgv.background = getDrawable(R.mipmap.white_arrow_forward_24_px)
            quiz_ox_title_tv.setTextColor(getColor(R.color.colorwhite))
            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
            Thread.sleep(1000)
        } else {
            quiz_ox_lay.setBackgroundColor(Color.parseColor("#ff5050"))
            quiz_ox_arrow_back_imgv.background = getDrawable(R.mipmap.white_arrow_forward_24_px)
            quiz_ox_title_tv.setTextColor(getColor(R.color.colorwhite))
            Toast.makeText(this, "오답입니다!", Toast.LENGTH_SHORT).show()
            Thread.sleep(1000)
        }

        if (quizData.questionOX.isNotEmpty()){
            var intent = Intent(this, OXTestActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            if(quizData.questionMultiple.isNotEmpty()){
                var intent = Intent(this, DragTestActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        finish()
    }
}