package com.shannan.moziopizza.feature.flavors.data

import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor

object DataFixtures {

    internal fun getFlavorApiModel(
        mbId: String = "mbId",
        name: String = "album"
    ): Flavor = Flavor(
        "fake flavor", 10f
    )


    object ApiResponse {
        internal fun getFlavors() = listOf(Flavor("fake flavor", 10f),
        )
    }
}
