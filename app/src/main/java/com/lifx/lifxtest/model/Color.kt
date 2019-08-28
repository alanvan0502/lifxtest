package com.lifx.lifxtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Color {

    @SerializedName("hue")
    var hue: Double? = null

    @SerializedName("saturation")
    var saturation: Double? = null

    @SerializedName("brightness")
    var brightness: Double? = null

    @SerializedName("kelvin")
    var kelvin: Double? = null

}