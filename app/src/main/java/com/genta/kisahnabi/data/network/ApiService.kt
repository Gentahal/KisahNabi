package com.genta.kisahnabi.data.network

import com.genta.kisahnabi.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi() : Flowable<List<KisahResponse>> //Api services seperti dao
}