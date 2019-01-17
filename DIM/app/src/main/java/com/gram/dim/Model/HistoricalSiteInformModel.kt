package com.gram.dim.Model

import com.google.gson.annotations.SerializedName

class HistoricalSiteInformModel {

    @SerializedName("explain")
    var explain: String = ""

    @SerializedName("extraText")
    var extraText: String = ""

    @SerializedName("imagePath")
    var imagePath: String = ""

    @SerializedName("location")
    var location: String = ""

    @SerializedName("text")
    var text: String = ""

    @SerializedName("extra")
    var extra: ArrayList<Extras> = ArrayList()

    class Extras {
        @SerializedName("extraImagePath")
        var extraImagePath: String = ""

        @SerializedName("extraLocation")
        var extraLocation: String = ""

        @SerializedName("extraName")
        var extraName: String = ""
    }

}
