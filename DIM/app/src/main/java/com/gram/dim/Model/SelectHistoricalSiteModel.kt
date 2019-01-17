package com.gram.dim.Model

import com.google.gson.annotations.SerializedName

class SelectHistoricalSiteModel {

    @SerializedName("historicalSiteImagePath")
    var imagePath: String = ""

    @SerializedName("historicalSiteLocation")
    var location: String = ""

    @SerializedName("historicalSiteName")
    var name: String = ""

}