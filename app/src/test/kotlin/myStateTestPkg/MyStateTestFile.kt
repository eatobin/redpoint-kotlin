package myStateTestPkg

import giftPairPkg.GiftPairDC
import giftPairPkg.JsonStringTA
import hatPkg.HatTA
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import myStatePkg.MyState
import myStatePkg.MyState.Companion.myStateDrawPuck
import myStatePkg.MyState.Companion.myStateErrors
import myStatePkg.MyState.Companion.myStateGiveeIsFailure
import myStatePkg.MyState.Companion.myStateGiveeIsSuccess
import myStatePkg.MyState.Companion.myStateJsonStringToMyState
import myStatePkg.MyState.Companion.myStatePrintResults
import myStatePkg.MyState.Companion.myStateSelectNewGiver
import myStatePkg.MyState.Companion.myStateStartNewYear
import playerPkg.PlayerDC
import playersPkg.PlayersTA
import playersPkg.playersGetMyGivee
import playersPkg.playersGetMyGiver

class MyStateTestC : StringSpec({
    val beatlesJson: JsonStringTA =
        """{"rosterName":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val beatlesJsonBad: JsonStringTA =
        """{"rosterName""The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val beatlesJsonBad2: JsonStringTA =
        """{"rosterNameX":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val hawksJson: JsonStringTA =
        """{"rosterName":"Blackhawks","rosterYear":2010,"players":{"TroBro":{"playerName":"Troy Brouwer","giftHistory":[{"givee":"DavBol","giver":"JoeQue"}]},"PatKan":{"playerName":"Patrick Kane","giftHistory":[{"givee":"BryBic","giver":"CriHue"}]},"JoeQue":{"playerName":"Joel Quenneville","giftHistory":[{"givee":"TroBro","giver":"AndLad"}]},"NikHja":{"playerName":"Niklas Hjalmarsson","giftHistory":[{"givee":"BreSea","giver":"BriCam"}]},"TomKop":{"playerName":"Tomas Kopecky","giftHistory":[{"givee":"CriHue","giver":"DunKei"}]},"BryBic":{"playerName":"Bryan Bickell","giftHistory":[{"givee":"MarHos","giver":"PatKan"}]},"AntNie":{"playerName":"Antti Niemi","giftHistory":[{"givee":"JonToe","giver":"MarHos"}]},"PatSha":{"playerName":"Patrick Sharp","giftHistory":[{"givee":"BriCam","giver":"DavBol"}]},"DunKei":{"playerName":"Duncan Keith","giftHistory":[{"givee":"TomKop","giver":"AdaBur"}]},"BriCam":{"playerName":"Brian Campbell","giftHistory":[{"givee":"NikHja","giver":"PatSha"}]},"BreSea":{"playerName":"Brent Seabrook","giftHistory":[{"givee":"KriVer","giver":"NikHja"}]},"KriVer":{"playerName":"Kris Versteeg","giftHistory":[{"givee":"AndLad","giver":"BreSea"}]},"MarHos":{"playerName":"Marian Hossa","giftHistory":[{"givee":"AntNie","giver":"BryBic"}]},"AndLad":{"playerName":"Andrew Ladd","giftHistory":[{"givee":"JoeQue","giver":"KriVer"}]},"DavBol":{"playerName":"Dave Bolland","giftHistory":[{"givee":"PatSha","giver":"TroBro"}]},"CriHue":{"playerName":"Cristobal Huet","giftHistory":[{"givee":"PatKan","giver":"TomKop"}]},"JonToe":{"playerName":"Jonathan Toews","giftHistory":[{"givee":"AdaBur","giver":"AntNie"}]},"AdaBur":{"playerName":"Adam Burish","giftHistory":[{"givee":"DunKei","giver":"JonToe"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
    val hawksState = MyState(
        "Blackhawks", 2010, sortedMapOf(
            "TroBro" to PlayerDC("Troy Brouwer", listOf(GiftPairDC("DavBol", "JoeQue"))),
            "PatKan" to PlayerDC("Patrick Kane", listOf(GiftPairDC("BryBic", "CriHue"))),
            "JoeQue" to PlayerDC("Joel Quenneville", listOf(GiftPairDC("TroBro", "AndLad"))),
            "NikHja" to PlayerDC("Niklas Hjalmarsson", listOf(GiftPairDC("BreSea", "BriCam"))),
            "TomKop" to PlayerDC("Tomas Kopecky", listOf(GiftPairDC("CriHue", "DunKei"))),
            "BryBic" to PlayerDC("Bryan Bickell", listOf(GiftPairDC("MarHos", "PatKan"))),
            "AntNie" to PlayerDC("Antti Niemi", listOf(GiftPairDC("JonToe", "MarHos"))),
            "PatSha" to PlayerDC("Patrick Sharp", listOf(GiftPairDC("BriCam", "DavBol"))),
            "DunKei" to PlayerDC("Duncan Keith", listOf(GiftPairDC("TomKop", "AdaBur"))),
            "BriCam" to PlayerDC("Brian Campbell", listOf(GiftPairDC("NikHja", "PatSha"))),
            "BreSea" to PlayerDC("Brent Seabrook", listOf(GiftPairDC("KriVer", "NikHja"))),
            "KriVer" to PlayerDC("Kris Versteeg", listOf(GiftPairDC("AndLad", "BreSea"))),
            "MarHos" to PlayerDC("Marian Hossa", listOf(GiftPairDC("AntNie", "BryBic"))),
            "AndLad" to PlayerDC("Andrew Ladd", listOf(GiftPairDC("JoeQue", "KriVer"))),
            "DavBol" to PlayerDC("Dave Bolland", listOf(GiftPairDC("PatSha", "TroBro"))),
            "CriHue" to PlayerDC("Cristobal Huet", listOf(GiftPairDC("PatKan", "TomKop"))),
            "JonToe" to PlayerDC("Jonathan Toews", listOf(GiftPairDC("AdaBur", "AntNie"))),
            "AdaBur" to PlayerDC("Adam Burish", listOf(GiftPairDC("DunKei", "JonToe")))
        ), 0, setOf(), setOf(), null, null, setOf(), "n"
    )
    val rinSta = PlayerDC("Ringo Starr", listOf(GiftPairDC("JohLen", "GeoHar")))
    val johLen = PlayerDC("John Lennon", listOf(GiftPairDC("PauMcc", "RinSta")))
    val geoHar = PlayerDC("George Harrison", listOf(GiftPairDC("RinSta", "PauMcc")))
    val pauMcc = PlayerDC("Paul McCartney", listOf(GiftPairDC("GeoHar", "JohLen")))
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

    val rinStaPlus = PlayerDC(
        "Ringo Starr", listOf(GiftPairDC("JohLen", "GeoHar"), GiftPairDC("RinSta", "RinSta"))
    )

    val geoWhoops = PlayerDC("geoWhoops", listOf(GiftPairDC("GeoHar", "PauMcc")))
    val pauYikes = PlayerDC("pauYikes", listOf(GiftPairDC("GeoHar", "PauMcc")))
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
