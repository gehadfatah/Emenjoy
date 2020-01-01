package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Videos(
        @SerializedName("ad")

        val ad: Ad,
        @SerializedName("next_page")

        val next_page: String?,
        @SerializedName("videos")

        val videos: List<Video>
)