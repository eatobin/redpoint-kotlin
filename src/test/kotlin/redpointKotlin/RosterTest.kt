package redpointKotlin

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
private val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
private val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
private val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))

private val players: Players = mapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

private val roster: Roster = Roster("The Beatles", 2014, players)

class RosterTest : StringSpec({

    "rosterName should return \"The Beatles\"" {
        roster.rosterName.shouldBe("The Beatles")
    }

//    "setName should set the Borrower name" {
//        setName("Borrower1", Borrower("Jack", 1)).shouldBe(br1)
//    }
//
//    "getMaxBooks should return the Borrower maxBooks" {
//        getMaxBooks(br1).shouldBe(1)
//    }
//
//    "setMaxBooks should set the Borrower maxBooks" {
//        setMaxBooks(11, br1).shouldBe(Borrower("Borrower1", 11))
//    }
//
//    "the Borrower string should print" {
//        borrowerToString(br1).shouldBe("Borrower1 (1 books)")
//    }

})
