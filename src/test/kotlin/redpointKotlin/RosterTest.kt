package redpointKotlin

import io.kotlintest.matchers.types.shouldBeNull
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val rinSta: Player = Player("Ringo Starr", mutableListOf(GiftPair("JohLen", "GeoHar")))
private val johLen: Player = Player("John Lennon", mutableListOf(GiftPair("PauMcc", "RinSta")))
private val geoHar: Player = Player("George Harrison", mutableListOf(GiftPair("RinSta", "PauMcc")))
private val pauMcc: Player = Player("Paul McCartney", mutableListOf(GiftPair("GeoHar", "JohLen")))

private val players: Players = mapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

private val roster: Roster = Roster("The Beatles", 2014, players)

class RosterTest : StringSpec({

    "rosterName should return \"The Beatles\"" {
        roster.rosterName.shouldBe("The Beatles")
    }

    "rosterYear should return 2014" {
        roster.rosterYear.shouldBe(2014)
    }

    "players should return players" {
        roster.players.shouldBe(players)
    }

    "getPlayer should return a player from a roster" {
        Roster.getPlayerInRoster(roster, "GeoHar").shouldBe(geoHar)
        Roster.getPlayerInRoster(roster, "GeoHarX").shouldBeNull()
    }

    "giftHistory should return a giftHistory from a player" {
        pauMcc.giftHistory.shouldBe(listOf(GiftPair("GeoHar", "JohLen")))
    }

    "giftPair should return a giftPair from a giftHistory" {
        listOf(GiftPair("GeoHar", "JohLen")).elementAt(0).shouldBe(GiftPair("GeoHar", "JohLen"))
    }

    "getGiftPairInRoster should return a giftPair from a roster given a playerKey" {
        Roster.getGiftPairInRoster(roster, "JohLen", 0).shouldBe(GiftPair("PauMcc", "RinSta"))
        Roster.getGiftPairInRoster(roster, "JohLenX", 0) shouldBe (GiftPair("null", "null"))
    }

    "givee should return givee from giftPair" {
        // FIXME
//        Roster.getGiftPairInRoster(roster, "JohLen", 0).givee.shouldBe("PauMccX")
    }

})
