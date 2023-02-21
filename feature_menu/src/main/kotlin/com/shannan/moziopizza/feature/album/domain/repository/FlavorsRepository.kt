package com.shannan.moziopizza.feature.album.domain.repository

import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.album.data.datasource.api.model.Flavor

internal interface FlavorsRepository {
    suspend fun getFlavors(): Result<List<Flavor>>
}
