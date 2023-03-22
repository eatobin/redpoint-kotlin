package rules

import giftPair.GiftPair
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import redpoint.*

class Rules2Test : StringSpec({
    val rinSta = Player("Ringo Starr", listOf(GiftPair(giver = "PauMcc", givee = "EriTob")))
    val johLen = Player("John Lennon", listOf(GiftPair(giver = "GeoHar", givee = "SusSmi")))
    val geoHar = Player("George Harrison", listOf(GiftPair(giver = "JohLen", givee = "DonDuc")))
    val pauMcc = Player("Paul McCartney", listOf(GiftPair(giver = "RinSta", givee = "MicMou")))
    val eriTob = Player("Eric Tobin", listOf(GiftPair(giver = "MicMou", givee = "RinSta")))
    val susSmi = Player("Susan Smith", listOf(GiftPair(giver = "DonDuc", givee = "JohLen")))
    val donDuc = Player("Donald Duck", listOf(GiftPair(giver = "SusSmi", givee = "GeoHar")))
    val micMou = Player("Mickey Mouse", listOf(GiftPair(giver = "EriTob", givee = "PauMcc")))
    val beatlesPlusPM: PlayersTA = sortedMapOf(
        "PauMcc" to pauMcc,
        "GeoHar" to geoHar,
        "RinSta" to rinSta,
        "JohLen" to johLen,
        "EriTob" to eriTob,
        "SusSmi" to susSmi,
        "DonDuc" to donDuc,
        "MicMou" to micMou
    )

    var beatlesPlus6 = playersAddYear(beatlesPlusPM)
    beatlesPlus6 = playersUpdateMyGivee("RinSta", "GeoHar", 1, beatlesPlus6)
    beatlesPlus6 = playersAddYear(beatlesPlus6)
    beatlesPlus6 = playersUpdateMyGivee("RinSta", "PauMcc", 2, beatlesPlus6)
    beatlesPlus6 = playersAddYear(beatlesPlus6)
    beatlesPlus6 = playersUpdateMyGivee("RinSta", "EriTob", 3, beatlesPlus6)
    beatlesPlus6 = playersAddYear(beatlesPlus6)
    beatlesPlus6 = playersUpdateMyGivee("RinSta", "SusSmi", 4, beatlesPlus6)
    beatlesPlus6 = playersAddYear(beatlesPlus6)
    beatlesPlus6 = playersUpdateMyGivee("RinSta", "DonDuc", 5, beatlesPlus6)
    beatlesPlus6 = playersAddYear(beatlesPlus6)
    beatlesPlus6 = playersUpdateMyGivee("RinSta", "MicMou", 6, beatlesPlus6)

    "A Player should not repeat for past four years" {
        giveeNotRepeat("RinSta", "DonDuc", 2, beatlesPlus6).shouldBeTrue()
        giveeNotRepeat("RinSta", "PauMcc", 2, beatlesPlus6).shouldBeTrue()
        giveeNotRepeat("RinSta", "EriTob", 2, beatlesPlus6).shouldBeFalse()
        giveeNotRepeat("RinSta", "GeoHar", 2, beatlesPlus6).shouldBeFalse()
        giveeNotRepeat("RinSta", "MicMou", 7, beatlesPlus6).shouldBeFalse()
        giveeNotRepeat("RinSta", "MicMou", 6, beatlesPlus6).shouldBeTrue()
        giveeNotRepeat("RinSta", "PauMcc", 6, beatlesPlus6).shouldBeFalse()
        giveeNotRepeat("RinSta", "GeoHar", 6, beatlesPlus6).shouldBeTrue()
    }
})
