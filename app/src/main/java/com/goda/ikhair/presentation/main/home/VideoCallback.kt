package com.goda.ikhair.presentation.main.home

import android.provider.MediaStore
import com.goda.ikhair.model.data_model.Video
import com.goda.ikhair.presentation.common.RecyclerViewCallback

interface VideoCallback :RecyclerViewCallback{
    fun likeClicked(v:Video)
    fun viewDone(v: Video)
    fun videoClicked(v: Video)
}