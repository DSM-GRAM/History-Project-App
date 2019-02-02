package com.gram.dim.Model

import com.google.gson.annotations.SerializedName

class TestModel{
    @SerializedName("oxQuestion")
    var questionOX: ArrayList<String> = ArrayList()

    @SerializedName("multipleQuestion")
    var questionMultiple: ArrayList<String> = ArrayList()

    @SerializedName("oxAnswer")
    var answerOX: ArrayList<String> = ArrayList()

    @SerializedName("multipleAnswer")
    var answerMultiple: ArrayList<String> = ArrayList()

    @SerializedName("wordOfNumber")
    var wordOfnumber: ArrayList<Int> = ArrayList()

    @SerializedName("wordList")
    var wordList: ArrayList<ArrayList<String>> = ArrayList()
}