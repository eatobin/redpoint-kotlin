package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.RosterStringCheck.lines
import redpointKotlin.RosterStringCheck.nonBlankString
import redpointKotlin.RosterStringCheck.removeName
import redpointKotlin.RosterStringCheck.scrub
import redpointKotlin.RosterStringCheck.scrubbedRosterString

class ApplyTest : StringSpec({

    val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
    val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"
    val bl = listOf("The Beatles,2014", "RinSta,Ringo Starr,JohLen,GeoHar", "JohLen,John Lennon,PauMcc,RinSta", "GeoHar,George Harrison,RinSta,PauMcc", "PauMcc,Paul McCartney,GeoHar,JohLen")
    val valid = ResultPair(null, "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen")

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
                ResultPair("the roster string was null, empty or only spaces", null)
        nonBlankString("") shouldBe
                ResultPair("the roster string was null, empty or only spaces", null)
        nonBlankString(" ") shouldBe
                ResultPair("the roster string was null, empty or only spaces", null)
        nonBlankString("This should pass") shouldBe
                ResultPair(null, "This should pass")
    }

    "raw-string should be scrubbed and fully valid" {
        scrubbedRosterString(ss) shouldBe valid
        scrubbedRosterString(null) shouldBe
                ResultPair("the roster string was null, empty or only spaces", null)
    }

})
