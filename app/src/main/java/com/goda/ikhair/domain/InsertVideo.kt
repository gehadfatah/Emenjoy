package com.goda.ikhair.domain

import com.goda.ikhair.domain.common.JobExecutor
import com.goda.ikhair.model.data.VideoDao
import com.goda.ikhair.model.data_model.Video


class InsertVideo  constructor(private val dao: VideoDao, private val executor: JobExecutor) {
    fun build(params: List<Video>) {
        executor.execute {
            dao.insertVideo(params)
        }
    }
}