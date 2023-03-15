import MyState.Companion.myStateDrawPuck
import MyState.Companion.myStateGiveeIsFailure
import MyState.Companion.myStateGiveeIsSuccess
import MyState.Companion.myStateJsonStringToMyState
import MyState.Companion.myStateStartNewYear
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MyStateTest : StringSpec({
    val beatlesJson: JsonStringTA =
        """{"rosterName":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val beatlesJsonBad: JsonStringTA =
        """{"rosterName""The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val beatlesJsonBad2: JsonStringTA =
        """{"rosterNameX":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""

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
        giveeHat = setOf(),
        giverHat = setOf(),
        maybeGivee = null,
        maybeGiver = null,
        discards = setOf(),
        quit = "n"
    )
    val testHat: HatTA = sortedSetOf("RinSta")

    val freshHat: HatTA = sortedSetOf("RinSta", "JohLen", "GeoHar", "PauMcc")

    val rinStaPlus = Player(
        "Ringo Starr", listOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta"))
    )

    "MyState should build from JSON" {
        myStateJsonStringToMyState(beatlesJson).shouldBe(beatlesState0)
    }
    "MyState should NOT build from BAD JSON" {
        shouldThrowAny {
            myStateJsonStringToMyState(beatlesJsonBad)
        }
    }
    "MyState should NOT build from BAD JSON 2" {
        shouldThrowAny {
            myStateJsonStringToMyState(beatlesJsonBad2)
        }
    }
    "MyState should draw a puck" {
        myStateDrawPuck(testHat).shouldBe("RinSta")
        myStateDrawPuck(sortedSetOf()).shouldBeNull()
    }
    "MyState should start a new year" {
        val beatlesState1 = myStateStartNewYear(beatlesState0)
        beatlesState1.giftYear.shouldBe(1)
        beatlesState1.giveeHat.shouldBe(freshHat)
        beatlesState1.giverHat.shouldBe(freshHat)
        beatlesState1.maybeGiver.shouldNotBeNull()
        beatlesState1.maybeGivee.shouldNotBeNull()
        beatlesState1.players["RinSta"].shouldBe(rinStaPlus)
        beatlesState1.discards.shouldBeEmpty()
    }
    "MyState should have a failing givee" {
        val beatlesState1 = myStateStartNewYear(beatlesState0)
        val badGivee = beatlesState1.maybeGivee!!
        val beatlesState2 = myStateGiveeIsFailure(beatlesState1)
        beatlesState2.giveeHat.shouldNotContain(badGivee)
        beatlesState2.maybeGivee.shouldNotBe(badGivee)
        beatlesState2.discards.shouldContain(badGivee)
    }
    "MyState should have a successful givee" {
        val beatlesState1 = myStateStartNewYear(beatlesState0)
        val goodGivee = beatlesState1.maybeGivee!!
        val goodGiver = beatlesState1.maybeGiver!!
        val beatlesState2 = myStateGiveeIsSuccess(beatlesState1)
        playersGetMyGivee(goodGiver, beatlesState2.players, beatlesState2.giftYear).shouldBe(goodGivee)
        playersGetMyGiver(goodGivee, beatlesState2.players, beatlesState2.giftYear).shouldBe(goodGiver)
        beatlesState2.giveeHat.shouldNotContain(goodGivee)
        beatlesState2.maybeGivee.shouldBeNull()
    }
})
