import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


private const val JSON_STRING: JsonStringTA =
    "{\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]},\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}}"
private const val BAD_JSON_STRING: JsonStringTA =
    "{\"PauMcc\":{\"playerName\"\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"
private const val BAD_JSON_STRING_2: JsonStringTA =
    "{\"PauMcc\":{\"playerNameX\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"

private val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
private val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
private val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
private val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
private val players: PlayersTA =
    sortedMapOf("PauMcc" to pauMcc, "GeoHar" to geoHar, "RinSta" to rinSta, "JohLen" to johLen)

private val newBee: Player = Player("New Bee", listOf(GiftPair("NewBee", "NewBee")))
private val newBeePlayers: PlayersTA =
    sortedMapOf("RinSta" to newBee, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

private val rinStaExt: Player =
    Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
private val johLenExt: Player =
    Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
private val geoHarExt: Player =
    Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
private val pauMccExt: Player =
    Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))
private val playersExt: PlayersTA =
    sortedMapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)


class PlayersTest : StringSpec({
    "players should build a sortedMap from JSON" {
        playersJsonStringToPlayers(JSON_STRING).shouldBe(players)
    }
    "players should NOT build a sortedMap from BAD JSON" {
        shouldThrowAny {
            playersJsonStringToPlayers(BAD_JSON_STRING)
        }
    }
    "players should NOT build a sortedMap from BAD JSON 2" {
        shouldThrowAny {
            playersJsonStringToPlayers(BAD_JSON_STRING_2)
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
})
