package com.shannan.moziopizza.feature.album.data

import com.shannan.moziopizza.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.shannan.moziopizza.feature.album.data.repository.FlavorsRepositoryImpl
import com.shannan.moziopizza.feature.album.domain.repository.FlavorsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<FlavorsRepository> { FlavorsRepositoryImpl(get()) }

    single { get<Retrofit>().create(AlbumRetrofitService::class.java) }
}
