import Players.Companion.playersJsonStringToPlayers
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


private const val JSON_STRING: JsonStringTA =
    "{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"
private const val BAD_JSON_STRING: JsonStringTA =
    "{\"PauMcc\":{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"

val rinSta: Player = Player("Ringo Starr", giftHistory = (GiftPair("JohLen", "GeoHar")))
val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
val players: PlayersTA = sortedMapOf("GeoHar" to geoHar, "JohLen" to johLen, "PauMcc" to pauMcc, "RinSta" to rinSta)

val newBee: Player = Player("New Bee", listOf(GiftPair("NewBee", "NewBee")))
val newBeePlayers: PlayersTA = sortedMapOf("RinSta" to newBee, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

class PlayersTest : StringSpec({
    "players should build from JSON" {
        playersJsonStringToPlayers(JSON_STRING).shouldBe(players)
    }
    "players should NOT build from BAD JSON" {
        shouldThrowAny {
            playersJsonStringToPlayers(BAD_JSON_STRING)
        }
    }
//    "players should return an updated player" {
//        playersUpdatePlayer("RinSta", Player("New Bee", listOf(GiftPair("NewBee", "NewBee"))), players).shouldBe(
//            newBeePlayers
//        )
//    }
//    "players should return a player name" {
//        playersGetPlayerName("PauMcc", players).shouldBe("Paul McCartney")
//    }
})
