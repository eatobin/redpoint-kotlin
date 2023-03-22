package redpoint

import giftPair.GiftPair
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    val jsonString: JsonStringTA =
        "{\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]},\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}}"
    val badJsonString: JsonStringTA =
        "{\"PauMcc\":{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"
    val badJsonString2: JsonStringTA =
        "{\"PauMcc\":{\"playerNameX\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"

    val rinSta = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
    val johLen = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
    val geoHar = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
    val pauMcc = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
    val players: PlayersTA = sortedMapOf("PauMcc" to pauMcc, "GeoHar" to geoHar, "RinSta" to rinSta, "JohLen" to johLen)

    val newBee = Player("New Bee", listOf(GiftPair("NewBee", "NewBee")))
    val newBeePlayers: PlayersTA =
        sortedMapOf("RinSta" to newBee, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

    val rinStaExt = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
    val johLenExt = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
    val geoHarExt = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
    val pauMccExt = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))
    val playersExt: PlayersTA =
        sortedMapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)

    val geoHarGivee = Player("George Harrison", listOf(GiftPair("you", "PauMcc")))
    val geoHarGiver = Player("George Harrison", listOf(GiftPair("RinSta", "you")))
    val playersGivee: PlayersTA =
        sortedMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHarGivee, "PauMcc" to pauMcc)
    val playersGiver: PlayersTA =
        sortedMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHarGiver, "PauMcc" to pauMcc)

    "players should build a sortedMap from JSON" {
        playersJsonStringToPlayers(jsonString).shouldBe(players)
    }
    "players should NOT build a sortedMap from BAD JSON" {
        shouldThrowAny {
            playersJsonStringToPlayers(badJsonString)
        }
    }
    "players should NOT build a sortedMap from BAD JSON 2" {
        shouldThrowAny {
            playersJsonStringToPlayers(badJsonString2)
        }
    }
    "players should return an updated player" {
        playersUpdatePlayer("RinSta", Player("New Bee", listOf(GiftPair("NewBee", "NewBee"))), players).shouldBe(
            newBeePlayers
        )
    }
    "players should return a player name" {
        playersGetPlayerName("PauMcc", players).shouldBe("Paul McCartney")
    }
    "players should add a new year" {
        playersAddYear(players).shouldBe(playersExt)
    }
    "players should return a givee and a giver" {
        playersGetMyGivee("GeoHar", players, 0).shouldBe("RinSta")
        playersGetMyGiver("GeoHar", players, 0).shouldBe("PauMcc")
    }
    "players should update a givee and a giver" {
        playersUpdateMyGivee("GeoHar", "you", 0, players).shouldBe(playersGivee)
        playersUpdateMyGiver("GeoHar", "you", 0, players).shouldBe(playersGiver)
    }
})
