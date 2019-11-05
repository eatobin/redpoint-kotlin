package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val giftPair: GiftPair = GiftPair("JohLen", "GeoHar")

class GiftPairTest : StringSpec({
    "A GiftPair should return its givee" {
        giftPair.givee.shouldBe("JohLen")
    }
})
