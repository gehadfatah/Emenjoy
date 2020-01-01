package com.goda.emenjoy.model.data_model

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("id")

        val idCategory: Int,
        @SerializedName("name")

        val nameCategory: String
){
        constructor():this(0,"")
}