package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.Players.getPlayer
import redpointKotlin.Players.setPlayer

val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
val players: PlayersT =
    mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

val newBee: Player = Player("New Bee", listOf(GiftPair("NewBee", "NewBee")))
val newBeePlayers: PlayersT =
    mutableMapOf("RinSta" to newBee, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

class PlayersTest : StringSpec({
    "Players should return a player" {
        getPlayer(players, "GeoHar").shouldBe(Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"))))
        getPlayer(players, "GeoHarX").shouldBe(Player("null", listOf(GiftPair("null", "null"))))
    }

    "Players should return an updated players" {
        setPlayer(players, "RinSta", Player("New Bee", listOf(GiftPair("NewBee", "NewBee")))).shouldBe(newBeePlayers)
    }
})
