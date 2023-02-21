package com.shannan.moziopizza.feature.album.domain

import com.shannan.moziopizza.feature.album.domain.usecase.GetFlavorListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {

    singleOf(::GetFlavorListUseCase)
}
