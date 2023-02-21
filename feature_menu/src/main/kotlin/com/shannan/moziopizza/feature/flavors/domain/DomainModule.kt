package com.shannan.moziopizza.feature.flavors.domain

import com.shannan.moziopizza.feature.flavors.domain.usecase.GetFlavorListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {

    singleOf(::GetFlavorListUseCase)
}
