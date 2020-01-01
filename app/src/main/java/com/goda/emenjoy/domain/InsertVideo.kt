package com.goda.emenjoy.domain

import com.goda.emenjoy.domain.common.JobExecutor
import com.goda.emenjoy.model.data.VideoDao
import com.goda.emenjoy.model.data_model.Video


class InsertVideo  constructor(private val dao: VideoDao, private val executor: JobExecutor) {
    fun build(params: List<Video>) {
        executor.execute {
            dao.insertVideo(params)
        }
    }
}