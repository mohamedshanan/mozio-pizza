package com.shannan.moziopizza.feature.album.data.datasource.api.service

import com.shannan.moziopizza.base.data.retrofit.ApiResult
import com.shannan.moziopizza.feature.album.data.datasource.api.model.Flavor
import retrofit2.http.GET

internal interface AlbumRetrofitService {
    @GET("mobile/tests/pizzas.json")
    suspend fun getPizzaFlavors(): ApiResult<List<Flavor>>
}
