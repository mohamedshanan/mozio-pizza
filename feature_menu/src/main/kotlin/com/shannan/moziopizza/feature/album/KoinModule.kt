package com.shannan.moziopizza.feature.album

import com.shannan.moziopizza.feature.album.data.dataModule
import com.shannan.moziopizza.feature.album.domain.domainModule
import com.shannan.moziopizza.feature.album.presentation.presentationModule

val featureAlbumModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
