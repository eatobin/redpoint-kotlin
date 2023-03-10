import MyState.Companion.myToJSON
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.*

class MyStateTest : StringSpec({
    val beatlesJson: JsonStringTA =
        """{"rosterName":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""

    val rinSta = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
    val johLen = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
    val geoHar = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
    val pauMcc = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
    val players: PlayersTA = sortedMapOf("PauMcc" to pauMcc, "GeoHar" to geoHar, "RinSta" to rinSta, "JohLen" to johLen)

    val beatlesState0 = MyState(
        rosterName = "The Beatles",
        rosterYear = 2014,
        players = players,
        giftYear = 0,
        giveeHat = TreeSet(),
        giverHat = TreeSet(),
        maybeGivee = null,
        maybeGiver = null,
        discards = TreeSet(),
        quit = "n"
    )

    "MyState should build from JSON" {
//        myStateJsonStringToMyState(beatlesJson).shouldBe(beatlesState0)
        myToJSON(beatlesState0).shouldBe("XXX")
    }
//    "player should NOT build from BAD JSON" {
//        shouldThrowAny {
//            playerJsonStringToPlayer(badJsonString)
//        }
//    }
//    "player should NOT build from BAD JSON 2" {
//        shouldThrowAny {
//            playerJsonStringToPlayer(badJsonString2)
//        }
//    }
//    "player should return an updated giftHistory" {
//        playerUpdateGiftHistory(listOf(GiftPair("nope", "yup")), player).shouldBe(
//            Player(
//                "Paul McCartney", listOf(GiftPair("nope", "yup"))
//            )
//        )
//    }
})
