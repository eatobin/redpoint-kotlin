package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.Players.addYearPlayers
import redpointKotlin.Players.getGivEeErPlayers
import redpointKotlin.Players.getPlayer
import redpointKotlin.Players.getPlayerNamePlayers
import redpointKotlin.Players.setGivEeErPlayers
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

    "Players should return an extended year players" {
        val rinSta = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
        val johLen = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
        val geoHar = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
        val pauMcc = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
        val players: PlayersT =
            mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

        val rinStaExt =
            Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
        val johLenExt =
            Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
        val geoHarExt =
            Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
        val pauMccExt =
            Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))
        val playersExt: PlayersT =
            mutableMapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)

        addYearPlayers(players).shouldBe(playersExt)
    }

    "Players should return a player's name" {
        getPlayerNamePlayers(players, "GeoHar").shouldBe("George Harrison")
    }

    "Players should return a givEeEr" {
        getGivEeErPlayers(players, "GeoHar", 0, "ee").shouldBe("RinSta")
        getGivEeErPlayers(players, "GeoHar", 0, "er").shouldBe("PauMcc")
    }

    "Players should set a givEeEr" {
        val rinSta = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
        val johLen = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
        val geoHar = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
        val pauMcc = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
        val players: PlayersT =
            mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

        val geoHarGivee = Player("George Harrison", listOf(GiftPair("you", "PauMcc")))
        val geoHarGiver = Player("George Harrison", listOf(GiftPair("you", "you")))
        val playersGivee: PlayersT =
            mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHarGivee, "PauMcc" to pauMcc)
        val playersGiver: PlayersT =
            mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHarGiver, "PauMcc" to pauMcc)

        setGivEeErPlayers(players, "GeoHar", 0, "you", "ee").shouldBe(playersGivee)
        setGivEeErPlayers(players, "GeoHar", 0, "you", "er").shouldBe(playersGiver)
    }
})
