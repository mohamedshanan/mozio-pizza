package com.shannan.moziopizza.feature.flavors.data.repository

import com.shannan.moziopizza.base.data.retrofit.ApiResult
import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor
import com.shannan.moziopizza.feature.flavors.data.datasource.api.service.FlavorsRetrofitService
import com.shannan.moziopizza.feature.flavors.domain.repository.FlavorsRepository
import timber.log.Timber

internal class FlavorsRepositoryImpl(
    private val flavorsRetrofitService: FlavorsRetrofitService,
) : FlavorsRepository {
    override suspend fun getFlavors(): Result<List<Flavor>> =
        when (val apiResult = flavorsRetrofitService.getPizzaFlavors()) {
            is ApiResult.Success -> {
                Result.Success(apiResult.data)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)
                Result.Failure()
            }
        }
}
