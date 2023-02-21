package com.shannan.moziopizza.feature.album.data.repository

import com.shannan.moziopizza.base.data.retrofit.ApiResult
import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.album.data.datasource.api.model.Flavor
import com.shannan.moziopizza.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.shannan.moziopizza.feature.album.domain.repository.FlavorsRepository
import timber.log.Timber

internal class FlavorsRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
) : FlavorsRepository {
    override suspend fun getFlavors(): Result<List<Flavor>> =
        when (val apiResult = albumRetrofitService.getPizzaFlavors()) {
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
