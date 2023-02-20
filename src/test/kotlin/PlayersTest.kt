import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


private const val JSON_STRING: JsonStringTA =
    "{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"
private const val BAD_JSON_STRING: JsonStringTA =
    "{\"PauMcc\":{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"

val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
val players: PlayersTA = mapOf("PauMcc" to pauMcc, "GeoHar" to geoHar, "JohLen" to johLen, "RinSta" to rinSta)

val newBee: Player = Player("New Bee", listOf(GiftPair("NewBee", "NewBee")))
val newBeePlayers: PlayersTA = mapOf("RinSta" to newBee, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

class PlayersTest : StringSpec({
    "players should build from JSON" {
        playersJsonStringToPlayers(JSON_STRING).shouldBe(players)
    }
    "players should NOT build from BAD JSON" {
        shouldThrowAny {
            playersJsonStringToPlayers(BAD_JSON_STRING)
        }
    }
    "players should return an updated player" {
        playersUpdatePlayer("RinSta", Player("New Bee", listOf(GiftPair("NewBee", "NewBee"))), players).shouldBe(
            newBeePlayers
        )
    }
})

//"Players" should "return an updated player" in {
//    assert(playersUpdatePlayer("RinSta")(Player("New Bee", Vector(GiftPair("NewBee", "NewBee"))))(players) == newBeePlayers)
//}

//     "Players should return an extended year players" {
//         val rinSta = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
//         val johLen = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
//         val geoHar = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
//         val pauMcc = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
//         val players: PlayersT =
//             mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

//         val rinStaExt =
//             Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
//         val johLenExt =
//             Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
//         val geoHarExt =
//             Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
//         val pauMccExt =
//             Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))
//         val playersExt: PlayersT =
//             mutableMapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)

//         addYearPlayers(players).shouldBe(playersExt)
//     }

//     "Players should return a player's name" {
//         getPlayerNamePlayers(players, "GeoHar").shouldBe("George Harrison")
//     }

//     "Players should return a givEeEr" {
//         getGivEeErPlayers(players, "GeoHar", 0, "ee").shouldBe("RinSta")
//         getGivEeErPlayers(players, "GeoHar", 0, "er").shouldBe("PauMcc")
//     }

//     "Players should set a givEeEr" {
//         val rinSta = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
//         val johLen = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
//         val geoHar = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
//         val pauMcc = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
//         val players: PlayersT =
//             mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

//         val geoHarGivee = Player("George Harrison", listOf(GiftPair("you", "PauMcc")))
//         val geoHarGiver = Player("George Harrison", listOf(GiftPair("you", "you")))
//         val playersGivee: PlayersT =
//             mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHarGivee, "PauMcc" to pauMcc)
//         val playersGiver: PlayersT =
//             mutableMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHarGiver, "PauMcc" to pauMcc)

//         setGivEeErPlayers(players, "GeoHar", 0, "you", "ee").shouldBe(playersGivee)
//         setGivEeErPlayers(players, "GeoHar", 0, "you", "er").shouldBe(playersGiver)
//     }

