package com.shannan.moziopizza.feature.flavors.data

import com.shannan.moziopizza.feature.flavors.data.datasource.api.service.FlavorsRetrofitService
import com.shannan.moziopizza.feature.flavors.data.repository.FlavorsRepositoryImpl
import com.shannan.moziopizza.feature.flavors.domain.repository.FlavorsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<FlavorsRepository> { FlavorsRepositoryImpl(get()) }

    single { get<Retrofit>().create(FlavorsRetrofitService::class.java) }
}
