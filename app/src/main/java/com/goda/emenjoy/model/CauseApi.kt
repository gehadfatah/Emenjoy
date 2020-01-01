package com.goda.emenjoy.model

import com.goda.emenjoy.model.common.retrofitClient
import com.goda.emenjoy.model.data_model.EmenjoyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

val CAUSE_API_DEP : CauseApi by lazy {
    retrofitClient.create(CauseApi::class.java)
}

interface CauseApi {

    @GET("api/public/home")
    fun getEnjoyVideos( @Query("page") page: Int = 1): Single<EmenjoyResponse>

}


