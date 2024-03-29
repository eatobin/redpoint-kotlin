package player

import giftPair.GiftPair
import giftPair.JsonStringTA
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import player.Player.Companion.playerJsonStringToPlayer
import player.Player.Companion.playerUpdateGiftHistory

class PlayerKtTest : StringSpec({
    val jsonString: JsonStringTA =
        "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val badJsonString: JsonStringTA =
        "{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val badJsonString2: JsonStringTA =
        "{\"playerNameX\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val badJsonString3: JsonStringTA =
        "{\"playerName\":\"Paul McCartney\",\"giftHistoryX\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val badJsonString4: JsonStringTA =
        "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"giveeX\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))

    "player should build from JSON" {
        playerJsonStringToPlayer(jsonString).shouldBe(player)
    }
    "player should NOT build from BAD JSON" {
        shouldThrowAny {
            playerJsonStringToPlayer(badJsonString)
        }
    }
    "player should NOT build from BAD JSON 2" {
        shouldThrowAny {
            playerJsonStringToPlayer(badJsonString2)
        }
    }
    "player should NOT build from BAD JSON 3" {
        shouldThrowAny {
            playerJsonStringToPlayer(badJsonString3)
        }
    }
    "player should NOT build from BAD JSON 4" {
        shouldThrowAny {
            playerJsonStringToPlayer(badJsonString4)
        }
    }
    "player should return an updated giftHistory" {
        playerUpdateGiftHistory(listOf(GiftPair("nope", "yup")), player).shouldBe(
            Player(
                "Paul McCartney", listOf(GiftPair("nope", "yup"))
            )
        )
    }
})
