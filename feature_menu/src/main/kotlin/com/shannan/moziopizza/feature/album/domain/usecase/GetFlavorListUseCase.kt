package com.shannan.moziopizza.feature.album.domain.usecase

import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.album.data.datasource.api.model.Flavor
import com.shannan.moziopizza.feature.album.domain.repository.FlavorsRepository

internal class GetFlavorListUseCase(
    private val flavorsRepository: FlavorsRepository,
) {

    suspend operator fun invoke(): Result<List<Flavor>> = flavorsRepository
        .getFlavors()
}
