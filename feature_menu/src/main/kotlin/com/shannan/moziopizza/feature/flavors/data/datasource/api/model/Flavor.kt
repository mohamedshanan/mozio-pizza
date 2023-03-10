package com.shannan.moziopizza.feature.flavors.data.datasource.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Flavor(
    val name: String,
    val price: Float,
)
