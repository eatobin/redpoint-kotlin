import MyState.Companion.myStateDrawPuck
import MyState.Companion.myStateErrors
import MyState.Companion.myStateGiveeIsFailure
import MyState.Companion.myStateGiveeIsSuccess
import MyState.Companion.myStateJsonStringToMyState
import MyState.Companion.myStatePrintResults
import MyState.Companion.myStateSelectNewGiver
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
    val hawksJson: JsonStringTA =
        """{"rosterName":"Blackhawks","rosterYear":2010,"players":{"TroBro":{"playerName":"Troy Brouwer","giftHistory":[{"givee":"DavBol","giver":"JoeQue"}]},"PatKan":{"playerName":"Patrick Kane","giftHistory":[{"givee":"BryBic","giver":"CriHue"}]},"JoeQue":{"playerName":"Joel Quenneville","giftHistory":[{"givee":"TroBro","giver":"AndLad"}]},"NikHja":{"playerName":"Niklas Hjalmarsson","giftHistory":[{"givee":"BreSea","giver":"BriCam"}]},"TomKop":{"playerName":"Tomas Kopecky","giftHistory":[{"givee":"CriHue","giver":"DunKei"}]},"BryBic":{"playerName":"Bryan Bickell","giftHistory":[{"givee":"MarHos","giver":"PatKan"}]},"AntNie":{"playerName":"Antti Niemi","giftHistory":[{"givee":"JonToe","giver":"MarHos"}]},"PatSha":{"playerName":"Patrick Sharp","giftHistory":[{"givee":"BriCam","giver":"DavBol"}]},"DunKei":{"playerName":"Duncan Keith","giftHistory":[{"givee":"TomKop","giver":"AdaBur"}]},"BriCam":{"playerName":"Brian Campbell","giftHistory":[{"givee":"NikHja","giver":"PatSha"}]},"BreSea":{"playerName":"Brent Seabrook","giftHistory":[{"givee":"KriVer","giver":"NikHja"}]},"KriVer":{"playerName":"Kris Versteeg","giftHistory":[{"givee":"AndLad","giver":"BreSea"}]},"MarHos":{"playerName":"Marian Hossa","giftHistory":[{"givee":"AntNie","giver":"BryBic"}]},"AndLad":{"playerName":"Andrew Ladd","giftHistory":[{"givee":"JoeQue","giver":"KriVer"}]},"DavBol":{"playerName":"Dave Bolland","giftHistory":[{"givee":"PatSha","giver":"TroBro"}]},"CriHue":{"playerName":"Cristobal Huet","giftHistory":[{"givee":"PatKan","giver":"TomKop"}]},"JonToe":{"playerName":"Jonathan Toews","giftHistory":[{"givee":"AdaBur","giver":"AntNie"}]},"AdaBur":{"playerName":"Adam Burish","giftHistory":[{"givee":"DunKei","giver":"JonToe"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val hawksState: MyState = MyState(
        "Blackhawks", 2010, sortedMapOf(
            "TroBro" to Player("Troy Brouwer", listOf(GiftPair("DavBol", "JoeQue"))),
            "PatKan" to Player("Patrick Kane", listOf(GiftPair("BryBic", "CriHue"))),
            "JoeQue" to Player("Joel Quenneville", listOf(GiftPair("TroBro", "AndLad"))),
            "NikHja" to Player("Niklas Hjalmarsson", listOf(GiftPair("BreSea", "BriCam"))),
            "TomKop" to Player("Tomas Kopecky", listOf(GiftPair("CriHue", "DunKei"))),
            "BryBic" to Player("Bryan Bickell", listOf(GiftPair("MarHos", "PatKan"))),
            "AntNie" to Player("Antti Niemi", listOf(GiftPair("JonToe", "MarHos"))),
            "PatSha" to Player("Patrick Sharp", listOf(GiftPair("BriCam", "DavBol"))),
            "DunKei" to Player("Duncan Keith", listOf(GiftPair("TomKop", "AdaBur"))),
            "BriCam" to Player("Brian Campbell", listOf(GiftPair("NikHja", "PatSha"))),
            "BreSea" to Player("Brent Seabrook", listOf(GiftPair("KriVer", "NikHja"))),
            "KriVer" to Player("Kris Versteeg", listOf(GiftPair("AndLad", "BreSea"))),
            "MarHos" to Player("Marian Hossa", listOf(GiftPair("AntNie", "BryBic"))),
            "AndLad" to Player("Andrew Ladd", listOf(GiftPair("JoeQue", "KriVer"))),
            "DavBol" to Player("Dave Bolland", listOf(GiftPair("PatSha", "TroBro"))),
            "CriHue" to Player("Cristobal Huet", listOf(GiftPair("PatKan", "TomKop"))),
            "JonToe" to Player("Jonathan Toews", listOf(GiftPair("AdaBur", "AntNie"))),
            "AdaBur" to Player("Adam Burish", listOf(GiftPair("DunKei", "JonToe")))
        ), 0, setOf(), setOf(), null, null, setOf(), "n"
    )
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

    val geoWhoops = Player("geoWhoops", listOf(GiftPair("GeoHar", "PauMcc")))
    val pauYikes = Player("pauYikes", listOf(GiftPair("GeoHar", "PauMcc")))
    val playersWeird = sortedMapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoWhoops, "PauMcc" to pauYikes)

    val weirdState = MyState(
        rosterName = "The Beatles",
        rosterYear = 2014,
        players = playersWeird,
        giftYear = 0,
        giveeHat = sortedSetOf(),
        giverHat = sortedSetOf(),
        maybeGivee = null,
        maybeGiver = null,
        discards = sortedSetOf(),
        quit = "n"
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
    "MyState should build from JSON-Hawks" {
        myStateJsonStringToMyState(hawksJson).shouldBe(hawksState)
//        assert(myStateJsonStringToMyState(hawksJson) == Right(hawksState))
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
    "MyState should select a new giver" {
        val beatlesState1 = myStateStartNewYear(beatlesState0)
        val badGivee = beatlesState1.maybeGivee!!
        val beatlesState2 = myStateGiveeIsFailure(beatlesState1)
        val goodGivee = beatlesState2.maybeGivee!!
        val goodGiver = beatlesState2.maybeGiver!!
        val beatlesState3 = myStateGiveeIsSuccess(beatlesState2)
        val beatlesState4 = myStateSelectNewGiver(beatlesState3)
        beatlesState4.giveeHat.shouldContain(badGivee)
        beatlesState4.giveeHat.shouldNotContain(goodGivee)
        beatlesState4.giverHat.shouldNotContain(goodGiver)
        beatlesState4.maybeGivee.shouldNotBe(goodGivee)
        beatlesState4.maybeGiver.shouldNotBe(goodGiver)
        beatlesState4.discards.shouldBeEmpty()
    }
    "MyState should report player errors" {
        myStateErrors(weirdState).shouldBe(listOf("GeoHar", "PauMcc"))
    }
    "MyState should print itself" {
        myStatePrintResults(beatlesState0).shouldBe(beatlesState0)
        myStatePrintResults(weirdState).shouldBe(weirdState)
    }
})
