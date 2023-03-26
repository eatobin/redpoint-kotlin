package rulesTestPkg

import giftPairPkg.GiftPairDC
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import playerPkg.PlayerDC
import playersPkg.PlayersTA
import playersPkg.playersAddYear
import playersPkg.playersUpdateMyGivee
import rulesPkg.rules2GiveeNotRepeat

class Rules2TestC : StringSpec({
    val rinSta = PlayerDC("Ringo Starr", listOf(GiftPairDC(giver = "PauMcc", givee = "EriTob")))
    val johLen = PlayerDC("John Lennon", listOf(GiftPairDC(giver = "GeoHar", givee = "SusSmi")))
    val geoHar = PlayerDC("George Harrison", listOf(GiftPairDC(giver = "JohLen", givee = "DonDuc")))
    val pauMcc = PlayerDC("Paul McCartney", listOf(GiftPairDC(giver = "RinSta", givee = "MicMou")))
    val eriTob = PlayerDC("Eric Tobin", listOf(GiftPairDC(giver = "MicMou", givee = "RinSta")))
    val susSmi = PlayerDC("Susan Smith", listOf(GiftPairDC(giver = "DonDuc", givee = "JohLen")))
    val donDuc = PlayerDC("Donald Duck", listOf(GiftPairDC(giver = "SusSmi", givee = "GeoHar")))
    val micMou = PlayerDC("Mickey Mouse", listOf(GiftPairDC(giver = "EriTob", givee = "PauMcc")))
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
        rules2GiveeNotRepeat("RinSta", "DonDuc", 2, beatlesPlus6).shouldBeTrue()
        rules2GiveeNotRepeat("RinSta", "PauMcc", 2, beatlesPlus6).shouldBeTrue()
        rules2GiveeNotRepeat("RinSta", "EriTob", 2, beatlesPlus6).shouldBeFalse()
        rules2GiveeNotRepeat("RinSta", "GeoHar", 2, beatlesPlus6).shouldBeFalse()
        rules2GiveeNotRepeat("RinSta", "MicMou", 7, beatlesPlus6).shouldBeFalse()
        rules2GiveeNotRepeat("RinSta", "MicMou", 6, beatlesPlus6).shouldBeTrue()
        rules2GiveeNotRepeat("RinSta", "PauMcc", 6, beatlesPlus6).shouldBeFalse()
        rules2GiveeNotRepeat("RinSta", "GeoHar", 6, beatlesPlus6).shouldBeTrue()
    }
})
