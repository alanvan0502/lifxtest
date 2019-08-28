package com.lifx.lifxtest

import com.lifx.lifxtest.model.Data
import io.reactivex.Observable
import retrofit2.http.GET

interface EndPoint {

    companion object {
        const val END_POINT = "https://cloud.lifx.com/themes/v1"
    }

    @GET("/curated")
    fun loadData(): Observable<List<Data>>
}