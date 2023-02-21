package com.shannan.moziopizza.feature.album.presentation

import coil.ImageLoader
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel
import com.shannan.moziopizza.feature.album.presentation.screen.pizzalist.FlavorListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {

    single { ImageLoader(get()) }

    // AlbumDetails
    viewModelOf(::OrderDetailsViewModel)

    viewModelOf(::FlavorListViewModel)
}
