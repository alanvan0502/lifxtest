package com.lifx.lifxtest

import com.lifx.lifxtest.model.Data
import io.reactivex.Observable

object Repository {

    private val service = NetworkServiceFactory.createService(EndPoint::class.java, EndPoint.END_POINT)

    fun loadData(): Observable<List<Data>> {
        return service.loadData()
    }
}