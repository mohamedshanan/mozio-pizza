package com.shannan.moziopizza.feature.flavors

import com.shannan.moziopizza.feature.flavors.data.dataModule
import com.shannan.moziopizza.feature.flavors.domain.domainModule
import com.shannan.moziopizza.feature.flavors.presentation.presentationModule

val featureAlbumModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
