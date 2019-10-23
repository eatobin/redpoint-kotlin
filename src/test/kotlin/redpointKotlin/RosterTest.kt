package redpointKotlin

import io.kotlintest.matchers.types.shouldBeNull
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
private val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
private val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
private val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))

private val players: Players = mapOf("RinSta" to rinSta, "JohLen" to johLen, "GeoHar" to geoHar, "PauMcc" to pauMcc)

private val roster: Roster = Roster("The Beatles", 2014, players)

private val rinStaExt: Player =
    Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
private val johLenExt: Player =
    Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
private val geoHarExt: Player =
    Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
private val pauMccExt: Player =
    Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))

private val playersExt: Players =
    mapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)

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

    "playerName should return a name from a player" {
        pauMcc.playerName.shouldBe("Paul McCartney")
    }


    "giftHistory should return a giftHistory from a player" {
        pauMcc.giftHistory.shouldBe(listOf(GiftPair("GeoHar", "JohLen")))
    }


    "getPlayerInRoster should return a player from a roster" {
        Roster.getPlayerInRoster(roster, "GeoHar").shouldBe(geoHar)
        Roster.getPlayerInRoster(roster, "GeoHarX").shouldBeNull()
    }

    "getGiftPairInRoster should return a giftPair from a roster given a playerKey" {
        Roster.getGiftPairInRoster(roster, "JohLen", 0).shouldBe(GiftPair("PauMcc", "RinSta"))
        Roster.getGiftPairInRoster(roster, "JohLenX", 0) shouldBe (GiftPair("null", "null"))
    }

    "getGiveeInRoster should return a givee from a roster given a playerKey and giftYear" {
        Roster.getGiveeInRoster(roster, "JohLen", 0).shouldBe("PauMcc")
        Roster.getGiveeInRoster(roster, "JohLenX", 0).shouldBe("null")
    }

    "getGiverInRoster should return a giver from a roster given a playerKey and giftYear" {
        Roster.getGiverInRoster(roster, "JohLen", 0).shouldBe("RinSta")
        Roster.getGiverInRoster(roster, "JohLenX", 0).shouldBe("null")
    }

    "giftPair should return a giftPair from a giftHistory" {
        pauMcc.giftHistory.elementAt(0).shouldBe(GiftPair("GeoHar", "JohLen"))
    }

    "addYearInPlayer should add a basic giftPair to player" {
        Player.addYearInPlayer(johLen, "NewKey")
            .shouldBe(Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta"), GiftPair("NewKey", "NewKey"))))
    }

    "addYearInPlayers should add a basic giftPair to each player i players" {
        Roster.addYearInPlayers(players)
            .shouldBe(playersExt)
    }

    "getPlayerNameInRoster should return a player name from a roster given a playerKey" {
        Roster.getPlayerNameInRoster(roster, "JohLen").shouldBe("John Lennon")
        Roster.getPlayerNameInRoster(roster, "JohLenX").shouldBe("null")
    }

})
