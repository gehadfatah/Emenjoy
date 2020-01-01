package com.goda.ikhair.domain

import com.goda.ikhair.model.data.VideoDao
import com.goda.ikhair.model.data_model.EmenjoyResponse
import com.goda.ikhair.model.data_model.Video
import io.reactivex.Flowable
import io.reactivex.Single

val causeListUseCasesDep by lazy {
    CauseListInteractor()
}

interface CauseListUseCases {
    fun getEnjoyVideos(page:Int): Single<EmenjoyResponse>
    fun insertVideo(videoDao: VideoDao,videos: List<Video>)
    fun getVideosListData(videoDao: VideoDao): Single<List<Video>>
    fun getDatabaseVideo(videoDao: VideoDao,id: Int): Single<Video>
    fun updateVideo(videoDao: VideoDao, video: Video)
}
val emptyVideoViewModel = Video()

