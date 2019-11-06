package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.Player.Companion.setGiftHistory

val player: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))

class PlayerTest : StringSpec({
    "A Player should return its playerName" {
        player.playerName.shouldBe("Ringo Starr")
    }

    "A Player should return its giftHistory" {
        player.giftHistory.shouldBe(listOf(GiftPair("JohLen", "GeoHar")))
    }

    "A Player should return an updated giftHistory" {
        setGiftHistory(player, listOf(GiftPair("nope", "yup"))).shouldBe(
            Player(
                "Ringo Starr",
                listOf(GiftPair("nope", "yup"))
            )
        )
    }
})
