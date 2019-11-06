package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.GiftHistory.addYear

val player: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))

class PlayerTest : StringSpec({
    "A Player should return its playerName" {
        player.playerName.shouldBe("Ringo Starr")
    }

//    "A GiftHistory should return a giftPair" {
//        getGiftPair(giftHistory, 0).shouldBe(GiftPair("JohLen", "GeoHar"))
//    }
//
//    "A GiftHistory should update a giftHistory" {
//        setGiftPair(giftHistory, 0, GiftPair("me", "you")).shouldBe(listOf(GiftPair("me", "you")))
//    }
})
