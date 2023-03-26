package playerTestPkg

import giftPairPkg.GiftPairDC
import giftPairPkg.JsonStringTA
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import playerPkg.PlayerDC
import playerPkg.PlayerDC.Companion.playerJsonStringToPlayer
import playerPkg.PlayerDC.Companion.playerUpdateGiftHistory

class PlayerTestC : StringSpec({
    val jsonString: JsonStringTA =
        "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val badJsonString: JsonStringTA =
        "{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val badJsonString2: JsonStringTA =
        "{\"playerNameX\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
    val player = PlayerDC("Paul McCartney", listOf(GiftPairDC("GeoHar", "JohLen")))

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
    "player should return an updated giftHistory" {
        playerUpdateGiftHistory(listOf(GiftPairDC("nope", "yup")), player).shouldBe(
            PlayerDC(
                "Paul McCartney", listOf(GiftPairDC("nope", "yup"))
            )
        )
    }
})
