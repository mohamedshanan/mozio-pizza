package com.shannan.moziopizza.feature.flavors.data.datasource.api.service

import com.shannan.moziopizza.base.data.retrofit.ApiResult
import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor
import retrofit2.http.GET

internal interface FlavorsRetrofitService {
    @GET("mobile/tests/pizzas.json")
    suspend fun getPizzaFlavors(): ApiResult<List<Flavor>>
}
