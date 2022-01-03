// import io.kotlintest.shouldBe
// import io.kotlintest.specs.StringSpec

// val rinStaR: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
// val johLenR: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
// val geoHarR: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
// val pauMccR: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
// val playersR: PlayersT =
//     mutableMapOf("RinSta" to rinStaR, "JohLen" to johLenR, "GeoHar" to geoHarR, "PauMcc" to pauMccR)
// val roster: Roster = Roster("The Beatles", 2014, playersR)

// class RosterTest : StringSpec({

//     "rosterName should return \"The Beatles\"" {
//         roster.rosterName.shouldBe("The Beatles")
//     }

//     "rosterYear should return 2014" {
//         roster.rosterYear.shouldBe(2014)
//     }

//     "players should return players" {
//         roster.players.shouldBe(playersR)
//     }

// })
