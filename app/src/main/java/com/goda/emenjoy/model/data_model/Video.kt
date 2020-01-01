package com.goda.emenjoy.model.data_model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Video(
        @NonNull
        @PrimaryKey
        @SerializedName("id")

        val id: Int,
        @Embedded

        @SerializedName("artist")

        val artist: Artist,
        @Embedded

        @SerializedName("category")

        val category: Category,
        @SerializedName("description")

        val description: String,

        @SerializedName("liked")

        var liked: Boolean,
        @SerializedName("likes")

        var likes: Int,
        @SerializedName("thumb_nail")

        val thumb_nail: String,
        @SerializedName("title")

        val title: String,
        @SerializedName("video")

        val video: String,
        @SerializedName("views")

        var views: Int
) {
    constructor() : this(0,Artist(), Category(), "", false, 0, "", "", "", 0

    )
}