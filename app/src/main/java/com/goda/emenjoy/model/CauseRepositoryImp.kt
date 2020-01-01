package com.goda.emenjoy.model

import com.goda.emenjoy.domain.common.JobExecutor
import com.goda.emenjoy.model.data.VideoDao
import com.goda.emenjoy.model.data_model.EmenjoyResponse
import com.goda.emenjoy.model.data_model.Video
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CauseRepositoryImp(private val causeApi: CauseApi = CAUSE_API_DEP/*,private val dao: VideoDao= AppDatabase.getDatabase(CausesApplication.applicationContext()).videoDao()*/,private val executor: JobExecutor= JobExecutor()) : CauseRepository {
    override fun getEnjoyVideos(page:Int): Single<EmenjoyResponse> = causeApi.getEnjoyVideos(page)
    override fun getDatabaseVideo(videoDao: VideoDao,params: Int): Single<Video> {
        return videoDao.getVideo(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    override fun insertVideo(videoDao: VideoDao,params: List<Video>) {
        executor.execute {
            videoDao.insertVideo(params)
        }
    }
    override fun updateVideo(videoDao: VideoDao,params: Video) {
        executor.execute {
            videoDao.updateVideo(params)
        }
    }
    override fun getVideosListData(videoDao: VideoDao): Single<List<Video>> {
        return videoDao.getVideosList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}