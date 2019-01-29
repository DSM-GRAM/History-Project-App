package com.gram.dim.Model

import com.google.gson.annotations.SerializedName

class TestModel{
    @SerializedName("questionOX")
    var questionOX: ArrayList<String> = ArrayList()

    @SerializedName("questionMultiple")
    var questionMultiple: ArrayList<String> = ArrayList()

    @SerializedName("answerOX")
    var answerOX: ArrayList<String> = ArrayList()

    @SerializedName("answerMultiple")
    var answerMultiple: ArrayList<String> = ArrayList()

    @SerializedName("answerOfnumber")
    var answerOfnumber: ArrayList<Int> = ArrayList()

    @SerializedName("word1")
    var word1: ArrayList<String> = ArrayList()

    @SerializedName("word2")
    var word2: ArrayList<String> = ArrayList()

    @SerializedName("word3")
    var word3: ArrayList<String> = ArrayList()

    @SerializedName("word4")
    var word4: ArrayList<String> = ArrayList()

    @SerializedName("word5")
    var word5: ArrayList<String> = ArrayList()

    @SerializedName("word6")
    var word6: ArrayList<String> = ArrayList()
}