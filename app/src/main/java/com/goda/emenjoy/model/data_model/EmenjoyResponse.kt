package com.goda.emenjoy.model.data_model

import com.google.gson.annotations.SerializedName

data class EmenjoyResponse(
        @SerializedName("data")

        val data: Data,
        @SerializedName("message")

        val message: String,
        @SerializedName("status_code")

        val status_code: Int,
        @SerializedName("success")

        val success: Boolean
)