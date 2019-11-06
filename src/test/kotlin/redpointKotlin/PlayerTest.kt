package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.GiftHistory.addYear

val player: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))

class PlayerTest : StringSpec({
    "A Player should return its playerName" {
        player.playerName.shouldBe("Ringo Starr")
    }

    "A Player should return its gifthistory" {
        player.giftHistory.shouldBe(listOf(GiftPair("JohLen", "GeoHar")))
    }


})
