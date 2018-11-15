package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.RosterStringCheck.applyOrError
import redpointKotlin.RosterStringCheck.lines
import redpointKotlin.RosterStringCheck.nonBlankString
import redpointKotlin.RosterStringCheck.removeName
import redpointKotlin.RosterStringCheck.scrub

class ApplyTest : StringSpec({

    val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
    val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"
    val bl = listOf("The Beatles,2014", "RinSta,Ringo Starr,JohLen,GeoHar", "JohLen,John Lennon,PauMcc,RinSta", "GeoHar,George Harrison,RinSta,PauMcc", "PauMcc,Paul McCartney,GeoHar,JohLen")

    "applyOrError should return one" {
        applyOrError(Pair(1, "two")) shouldBe "not-null"
    }

    "applyOrError should return two" {
        applyOrError(Pair(11, null)) shouldBe "null"
    }

    "scrub should remove \", \" and \\n" {
        scrub(bs) shouldBe ss
    }

    "lines should make a List of Strings" {
        lines(ss) shouldBe bl
    }

    "removeName should drop player name - only symbols" {
        removeName(listOf("RinSta", "Ringo Starr", "JohLen", "GeoHar")) shouldBe
                listOf("RinSta", "JohLen", "GeoHar")
    }

    "nonBlankString should error only for null, empty or only spaces input" {
        nonBlankString(null) shouldBe
                Pair("the roster string was null, empty or only spaces", null)
        nonBlankString("") shouldBe
                Pair("the roster string was null, empty or only spaces", null)
        nonBlankString(" ") shouldBe
                Pair("the roster string was null, empty or only spaces", null)
        nonBlankString("This should pass") shouldBe
                Pair(null, "This should pass")
    }

})
