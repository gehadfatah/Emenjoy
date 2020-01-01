package com.goda.emenjoy.model.data_model

import com.google.gson.annotations.SerializedName

data class Artist(
        @SerializedName("id")

        val idArtist: Int,
        @SerializedName("image")

        val image: String,
        @SerializedName("name")

        val nameArtist: String,
        @SerializedName("price")

        val price: Int
){
        constructor():this(0,"","",0)
}