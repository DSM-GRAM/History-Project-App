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

    @SerializedName("wordList")
    var wordList: ArrayList<String> = ArrayList()

    class WordListModel{
        var word1: String = ""

        var word2: String = ""

        var word3: String = ""

        var word4: String = ""

        var word5: String = ""

        var word6: String = ""
    }
}