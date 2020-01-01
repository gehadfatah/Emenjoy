package com.goda.emenjoy.domain


import com.goda.emenjoy.domain.common.SingleUseCase
import com.goda.emenjoy.model.data.VideoDao
import com.goda.emenjoy.model.data_model.Video
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetVideo  constructor(private val dao: VideoDao) :
    SingleUseCase<Int,Video>() {
    override fun build(params: Int): Single<Video> {
        return dao.getVideo(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}