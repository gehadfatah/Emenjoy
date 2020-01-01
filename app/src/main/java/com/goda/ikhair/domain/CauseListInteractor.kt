package com.goda.ikhair.domain

import com.goda.ikhair.domain.common.mapNetworkErrors
import com.goda.ikhair.model.CauseRepository
import com.goda.ikhair.model.coinMarketCapRepositoryDep
import com.goda.ikhair.model.data.VideoDao
import com.goda.ikhair.model.data_model.EmenjoyResponse
import com.goda.ikhair.model.data_model.Video
import io.reactivex.Flowable
import io.reactivex.Single



class CauseListInteractor(private val causeRepository: CauseRepository = coinMarketCapRepositoryDep) : CauseListUseCases {


    override fun getEnjoyVideos(page:Int): Single<EmenjoyResponse> {
        return causeRepository.getEnjoyVideos(page)
                .mapNetworkErrors()

    }
    override fun getDatabaseVideo(videoDao: VideoDao, id:Int): Single<Video> {
        return causeRepository.getDatabaseVideo(videoDao,id)
                .mapNetworkErrors()

    }
    override fun getVideosListData(videoDao: VideoDao): Single<List<Video>> {
        return causeRepository.getVideosListData(videoDao)
                .mapNetworkErrors()

    }
    override fun insertVideo(videoDao: VideoDao,videos:List<Video>) {
        return causeRepository.insertVideo(videoDao,videos)
               /* .mapNetworkErrors()*/

    }
    override fun updateVideo(videoDao: VideoDao,video:Video) {
        return causeRepository.updateVideo(videoDao,video)
        /* .mapNetworkErrors()*/

    }

}