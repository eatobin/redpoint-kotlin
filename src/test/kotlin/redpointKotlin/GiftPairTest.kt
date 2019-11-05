package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.GiftPair.Companion.setGivEeEr

private val giftPair: GiftPair = GiftPair("JohLen", "GeoHar")

class GiftPairTest : StringSpec({
    "A GiftPair should return its givee" {
        giftPair.givee.shouldBe("JohLen")
    }

    "A GiftPair should return its giver" {
        giftPair.giver.shouldBe("GeoHar")
    }

    "A GiftPair should return an updated givEeEr" {
        setGivEeEr(giftPair, "NewBee", "ee").shouldBe(GiftPair("NewBee", "GeoHar"))
        setGivEeEr(giftPair, "NewBee", "er").shouldBe(GiftPair("JohLen", "NewBee"))
    }
})
