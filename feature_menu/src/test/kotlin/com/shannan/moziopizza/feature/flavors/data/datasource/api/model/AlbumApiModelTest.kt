package com.shannan.moziopizza.feature.flavors.data.datasource.api.model

import com.shannan.moziopizza.feature.flavors.data.DataFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumApiModelTest {

    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getFlavorApiModel()

        // when
        val domainModel = cut

        // then
        domainModel shouldBeEqualTo Flavor(
            cut.name,
            cut.price
        )
    }
}
