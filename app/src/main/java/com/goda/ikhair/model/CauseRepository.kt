package com.goda.ikhair.model

import com.goda.ikhair.model.data.VideoDao
import com.goda.ikhair.model.data_model.EmenjoyResponse
import com.goda.ikhair.model.data_model.Video
import io.reactivex.Flowable
import io.reactivex.Single

val coinMarketCapRepositoryDep by lazy {
    CauseRepositoryImp()
}

interface CauseRepository {
    fun getEnjoyVideos(page:Int): Single<EmenjoyResponse>
    fun insertVideo(videoDao: VideoDao,params: List<Video>)
    fun getVideosListData(videoDao: VideoDao): Single<List<Video>>
    fun getDatabaseVideo(videoDao: VideoDao, params: Int): Single<Video>
    fun updateVideo(videoDao: VideoDao, params: Video)
}