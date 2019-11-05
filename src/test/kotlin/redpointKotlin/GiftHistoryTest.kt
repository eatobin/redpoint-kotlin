package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.GiftHistory.addYear
import redpointKotlin.GiftHistory.getGiftPair
import redpointKotlin.GiftHistory.setGiftPair

class GiftHistoryTest : StringSpec({
    "A GiftHistory should add a new year" {
        val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))
        addYear(giftHistory, "NewBee").shouldBe(
            mutableListOf(
                GiftPair("JohLen", "GeoHar"),
                GiftPair("NewBee", "NewBee")
            )
        )
    }

    "A GiftHistory should return a giftPair" {
        val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))
        getGiftPair(giftHistory, 0).shouldBe(GiftPair("JohLen", "GeoHar"))
    }

    "A GiftHistory should return an updated giftHistory" {
        val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))
        setGiftPair(giftHistory, 0, GiftPair("me", "you")).shouldBe(mutableListOf(GiftPair("me", "you")))
    }
})
