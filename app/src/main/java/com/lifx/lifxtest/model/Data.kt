package com.lifx.lifxtest.model

import com.google.gson.annotations.SerializedName


class Data {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("order")
    var order: Int? = null

    @SerializedName("image_url")
    var imageUrl: String? = null

    @SerializedName("colors")
    var colors: List<Color>? = null

    @SerializedName("uuid")
    var uuid: String? = null

    @SerializedName("metadata")
    var metadata: Metadata? = null

    @SerializedName("analytics")
    var analytics: String? = null

    @SerializedName("invocation")
    var invocation: String? = null

}