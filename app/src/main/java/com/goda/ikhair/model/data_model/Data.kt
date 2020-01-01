package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("slider")

        val slider: List<Slider>,
        @SerializedName("videos")

        val videos: Videos
)