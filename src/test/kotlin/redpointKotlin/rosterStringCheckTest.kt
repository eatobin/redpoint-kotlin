package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import redpointKotlin.RosterStringCheck.lines
import redpointKotlin.RosterStringCheck.nonBlankString
import redpointKotlin.RosterStringCheck.removeName
import redpointKotlin.RosterStringCheck.rosterInfoLinePresent
import redpointKotlin.RosterStringCheck.scrub
import redpointKotlin.RosterStringCheck.scrubbedRosterString
import redpointKotlin.RosterStringCheck.validLengthString

class RosterStringCheckTest : StringSpec({

    val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
    val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"
    val bl = listOf("The Beatles,2014", "RinSta,Ringo Starr,JohLen,GeoHar", "JohLen,John Lennon,PauMcc,RinSta", "GeoHar,George Harrison,RinSta,PauMcc", "PauMcc,Paul McCartney,GeoHar,JohLen")
    val valid = ResultPair(null, "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen")


    val tooShort = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc"
    val noInfo = "\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
//    scrubbedRosterString(",2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
//    scrubbedRosterString("The Beatles\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
//    scrubbedRosterString("The Beatles, 2014P\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
//    scrubbedRosterString("The Beatles, 2096\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
//    scrubbedRosterString("The Beatles, 1896\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
//    scrubbedRosterString("The Beatles, 2014\nRinStaX, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
//    scrubbedRosterString("The Beatles, 2014\nRinSta, Ringo Starr, JohLen\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")


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

    "validLengthString should error for less than 4 \\n" {
        validLengthString(ss) shouldBe valid
        validLengthString(tooShort) shouldBe
                ResultPair("roster string is not long enough", null)
    }

    "rosterInfoLinePresent should error if no info line" {
        rosterInfoLinePresent(ss) shouldBe valid
        rosterInfoLinePresent(noInfo) shouldBe
                ResultPair("the roster info line is blank", null)
    }









    "raw-string should be scrubbed and fully valid" {
        scrubbedRosterString(ss) shouldBe valid
        scrubbedRosterString(null) shouldBe
                ResultPair("the roster string was null, empty or only spaces", null)
        scrubbedRosterString(tooShort) shouldBe
                ResultPair("roster string is not long enough", null)
        scrubbedRosterString(noInfo) shouldBe
                ResultPair("the roster info line is blank", null)
    }

})
