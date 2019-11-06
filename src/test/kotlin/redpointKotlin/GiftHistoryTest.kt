package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.GiftHistory.addYear
import redpointKotlin.GiftHistory.getGiftPair
import redpointKotlin.GiftHistory.setGiftPair

val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))

class GiftHistoryTest : StringSpec({
    "A GiftHistory should add a new year" {
        addYear(giftHistory, "NewBee").shouldBe(
            mutableListOf(
                GiftPair("JohLen", "GeoHar"),
                GiftPair("NewBee", "NewBee")
            )
        )
    }

    "A GiftHistory should return a giftPair" {
        getGiftPair(giftHistory, 0).shouldBe(GiftPair("JohLen", "GeoHar"))
    }

    "A GiftHistory should update a giftHistory" {
        setGiftPair(giftHistory, 0, GiftPair("me", "you")).shouldBe(mutableListOf(GiftPair("me", "you")))
    }
})
