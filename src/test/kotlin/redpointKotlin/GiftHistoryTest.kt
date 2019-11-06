package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.GiftHistory.addYear
import redpointKotlin.GiftHistory.getGiftPair
import redpointKotlin.GiftHistory.setGiftPair

class GiftHistoryTest : StringSpec({
    "A GiftHistory should add a new year" {
        val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))
        val giftHistory2: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"), GiftPair("NewBee", "NewBee"))
        addYear(giftHistory, "NewBee")
        giftHistory.shouldBe(giftHistory2)
    }

    "A GiftHistory should return a giftPair" {
        val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))
        getGiftPair(giftHistory, 0).shouldBe(GiftPair("JohLen", "GeoHar"))
    }

    "A GiftHistory should update a giftHistory" {
        val giftHistory: GiftHistoryT = mutableListOf(GiftPair("JohLen", "GeoHar"))
        val giftHistory2: GiftHistoryT = mutableListOf(GiftPair("me", "you"))
        setGiftPair(giftHistory, 0, GiftPair("me", "you"))
        giftHistory.shouldBe(giftHistory2)
    }
})
