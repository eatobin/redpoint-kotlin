package rulesTestPkg

import giftPairPkg.GiftPairDC
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import playerPkg.PlayerDC
import playersPkg.PlayersTA
import rulesPkg.rules1GiveeNotRecip
import rulesPkg.rules1GiveeNotSelf

class Rules1TestC : StringSpec({
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

    "A Player should not give to itself" {
        rules1GiveeNotSelf("RinSta", "GeoHar").shouldBeTrue()
        rules1GiveeNotSelf("RinSta", "RinSta").shouldBeFalse()
    }
    "A Player should not give to it's recip" {
        rules1GiveeNotRecip("RinSta", "JohLen", 0, beatlesPlusPM).shouldBeTrue()
        rules1GiveeNotRecip("RinSta", "EriTob", 0, beatlesPlusPM).shouldBeFalse()
    }
})
