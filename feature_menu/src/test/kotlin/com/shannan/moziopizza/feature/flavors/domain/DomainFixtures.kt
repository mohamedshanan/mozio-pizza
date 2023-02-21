package com.shannan.moziopizza.feature.flavors.domain

import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor

object DomainFixtures {

    internal fun getFlavors(
        name: String = "name",
        price: Float = 0.0f
    ): Flavor = Flavor(name, price)
}
