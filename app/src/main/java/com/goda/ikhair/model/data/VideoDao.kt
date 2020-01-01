package com.goda.ikhair.model.data

import androidx.room.*
import com.goda.ikhair.model.data_model.Video
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface VideoDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(decision: List<Video>)
    @Update
    fun updateVideo(video: Video)
    @Query("select * from video")
    fun getVideosList() : Single<List<Video>>

    @Query("select * from video where id =:id")
    fun getVideo(id:Int) : Single<Video>
}