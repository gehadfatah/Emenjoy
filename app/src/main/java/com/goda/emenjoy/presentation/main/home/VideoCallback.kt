package com.goda.emenjoy.presentation.main.home

import com.goda.emenjoy.model.data_model.Video
import com.goda.emenjoy.presentation.common.RecyclerViewCallback

interface VideoCallback :RecyclerViewCallback{
    fun likeClicked(v:Video)
    fun viewDone(v: Video)
    fun videoClicked(v: Video)
}