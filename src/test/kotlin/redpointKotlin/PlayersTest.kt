package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.Players.getPlayer

val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
val players: PlayersT =
    mapOf<PlayerKeyT, Player>("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

class PlayersTest : StringSpec({
    "Players should return a player" {
        getPlayer(players, "GeoHar").shouldBe(Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"))))
    }

//    "A Player should return its giftHistory" {
//        player.giftHistory.shouldBe(listOf(GiftPair("JohLen", "GeoHar")))
//    }
//
//    "A Player should return an updated giftHistory" {
//        setGiftHistory(player, listOf(GiftPair("nope", "yup"))).shouldBe(
//            Player(
//                "Ringo Starr",
//                listOf(GiftPair("nope", "yup"))
//            )
//        )
//    }
//
//    "A Player should return an extended giftHistory" {
//        addYearPlayer(player, "mee").shouldBe(
//            Player(
//                "Ringo Starr",
//                listOf(
//                    GiftPair("JohLen", "GeoHar"),
//                    GiftPair("mee", "mee")
//                )
//            )
//        )
//    }
})
