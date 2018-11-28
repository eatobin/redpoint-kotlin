package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class RosterStringCheckTest : StringSpec({

    val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
    val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"
    val ssEAT = EatResult(null, "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen")
    val bl = listOf("The Beatles,2014", "RinSta,Ringo Starr,JohLen,GeoHar", "JohLen,John Lennon,PauMcc,RinSta", "GeoHar,George Harrison,RinSta,PauMcc", "PauMcc,Paul McCartney,GeoHar,JohLen")
    val validEAT = EatResult(null, "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen")
    val valid = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"


    val tooShort = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc"
    val noInfo = "\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val noName = ",2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val noYear = "The Beatles\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val yearLetter = "The Beatles, 2014P\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val yearBig = "The Beatles, 2096\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val yearSmall = "The Beatles, 1896\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val badSym = "The Beatles, 2014\nRinStaX, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
    val missingSym = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"


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

    "nonBlankString should errorOrNull only for null, empty or only spaces input" {
        nonBlankString(null) shouldBe
                EatResult("the roster string was null, empty or only spaces", null)
        nonBlankString("") shouldBe
                EatResult("the roster string was null, empty or only spaces", null)
        nonBlankString(" ") shouldBe
                EatResult("the roster string was null, empty or only spaces", null)
        nonBlankString("This should pass") shouldBe
                EatResult(null, "This should pass")
    }

//    "validLengthString should errorOrNull for less than 4 \\n" {
//        validLengthString(ss) shouldBe valid
//        validLengthString(tooShort) shouldBe
//                EatResult("roster string is not long enough", null)
//    }

    "validLengthString2 should errorOrNull for less than 4 \\n" {
        validLengthString2(ssEAT) shouldBe validEAT
//        validLengthString(tooShort) shouldBe
//                EatResult("roster string is not long enough", null)
    }

//    "rosterInfoLinePresent should errorOrNull if no info line" {
//        rosterInfoLinePresent(ss) shouldBe valid
//        rosterInfoLinePresent(noInfo) shouldBe
//                EatResult("the roster info line is blank", null)
//    }
//
//    "namePresent should errorOrNull if no roster name" {
//        namePresent(ss) shouldBe valid
//        namePresent(noName) shouldBe
//                EatResult("the name value is missing", null)
//    }
//
//    "yearPresent should errorOrNull if no roster year" {
//        yearPresent(ss) shouldBe valid
//        yearPresent(noYear) shouldBe
//                EatResult("the year value is missing", null)
//    }
//
//    "yearTextAllDigits should errorOrNull if digits in year" {
//        yearTextAllDigits(ss) shouldBe valid
//        yearTextAllDigits(yearLetter) shouldBe
//                EatResult("the year value is not all digits", null)
//    }
//
//    "yearInRange should errorOrNull if year > 2056 or year ,1956" {
//        yearInRange(ss) shouldBe valid
//        yearInRange(yearBig) shouldBe
//                EatResult("not 1956 <= year <= 2056", null)
//        yearInRange(yearSmall) shouldBe
//                EatResult("not 1956 <= year <= 2056", null)
//    }
//
//
    "raw-string should be scrubbed and fully valid" {
        scrubbedRosterString(ss) shouldBe validEAT
//        scrubbedRosterString(null) shouldBe
//                EatResult("the roster string was null, empty or only spaces", null)
//        scrubbedRosterString(tooShort) shouldBe
//                EatResult("roster string is not long enough", null)
//        scrubbedRosterString(noInfo) shouldBe
//                EatResult("the roster info line is blank", null)
//        scrubbedRosterString(noName) shouldBe
//                EatResult("the name value is missing", null)
//        scrubbedRosterString(noYear) shouldBe
//                EatResult("the year value is missing", null)
//        scrubbedRosterString(yearLetter) shouldBe
//                EatResult("the year value is not all digits", null)
//        scrubbedRosterString(yearBig) shouldBe
//                EatResult("not 1956 <= year <= 2056", null)
//        scrubbedRosterString(yearSmall) shouldBe
//                EatResult("not 1956 <= year <= 2056", null)
//        scrubbedRosterString(badSym) shouldBe
//                EatResult("the players sub-string is invalid", null)
//        scrubbedRosterString(missingSym) shouldBe
//                EatResult("the players sub-string is invalid", null)
    }

})
