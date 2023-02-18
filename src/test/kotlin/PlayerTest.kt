import Player.Companion.playerJsonStringToPlayer
import Player.Companion.playerUpdateGiftHistory
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private const val JSON_STRING: JsonStringTA =
    "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
private const val BAD_JSON_STRING: JsonStringTA =
    "{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
val player: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))

class PlayerTest : StringSpec({
    "player should build from JSON" {
        playerJsonStringToPlayer(JSON_STRING).shouldBe(player)
    }
    "player should NOT build from BAD JSON" {
        shouldThrowAny {
            playerJsonStringToPlayer(BAD_JSON_STRING)
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
