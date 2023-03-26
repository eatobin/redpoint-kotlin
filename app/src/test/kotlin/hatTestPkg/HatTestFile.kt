package hatTestPkg

import giftPairPkg.GiftPairDC
import giftPairPkg.JsonStringTA
import hatPkg.*
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import playerPkg.PlayerDC
import playersPkg.PlayersTA

class HatTestC : StringSpec({
    val jsonString: JsonStringTA = "[\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
    val badJsonString: JsonStringTA = "[\"RinSta\"\"JohLen\"\"GeoHar\"\"PauMcc\"]"
    val duplicates: JsonStringTA = "[\"RinSta\",\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
    val testHat: HatTA = sortedSetOf("JohLen", "RinSta", "PauMcc", "GeoHar")

    val rinSta = PlayerDC("Ringo Starr", listOf(GiftPairDC("JohLen", "GeoHar")))
    val johLen = PlayerDC("John Lennon", listOf(GiftPairDC("PauMcc", "RinSta")))
    val geoHar = PlayerDC("George Harrison", listOf(GiftPairDC("RinSta", "PauMcc")))
    val pauMcc = PlayerDC("Paul McCartney", listOf(GiftPairDC("GeoHar", "JohLen")))
    val players: PlayersTA = sortedMapOf("PauMcc" to pauMcc, "GeoHar" to geoHar, "RinSta" to rinSta, "JohLen" to johLen)

    "hat should build from JSON" {
        hatJsonStringToHat(jsonString).shouldBe(testHat)
    }
    "hat should NOT build a sortedSet from BAD JSON" {
        shouldThrowAny {
            hatJsonStringToHat(badJsonString)
        }
    }
    "hat should NOT build a non-Set from DUPLICATES" {
        hatJsonStringToHat(duplicates).shouldBe(testHat)
    }
    "hat should remove a puck" {
        hatRemovePuck("RinSta", testHat).shouldBe(sortedSetOf("JohLen", "PauMcc", "GeoHar"))
        hatRemovePuck("RinStaX", sortedSetOf()).shouldBe(sortedSetOf())
    }
    "hat should discard a puck" {
        hatDiscardGivee("JohLen", sortedSetOf("PauMcc")).shouldBe(sortedSetOf("PauMcc", "JohLen"))
    }
    "hat should return discarded givees" {
        hatReturnDiscards(sortedSetOf("GeoHar"), sortedSetOf("PauMcc", "JohLen")).shouldBe(
            sortedSetOf(
                "JohLen", "PauMcc", "GeoHar"
            )
        )
    }
    "hat should make itself given players" {
        hatMakeHat(players).shouldBe(testHat)
    }
})
