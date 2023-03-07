import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private const val JSON_STRING: JsonStringTA = "[\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
private const val BAD_JSON_STRING: JsonStringTA = "[\"RinSta\"\"JohLen\"\"GeoHar\"\"PauMcc\"]"
private const val DUPLICATES: JsonStringTA = "[\"RinSta\",\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
private val testHat: HatTA = sortedSetOf("JohLen", "RinSta", "PauMcc", "GeoHar")

private val rinSta: Player = Player("Ringo Starr", listOf(GiftPair("JohLen", "GeoHar")))
private val johLen: Player = Player("John Lennon", listOf(GiftPair("PauMcc", "RinSta")))
private val geoHar: Player = Player("George Harrison", listOf(GiftPair("RinSta", "PauMcc")))
private val pauMcc: Player = Player("Paul McCartney", listOf(GiftPair("GeoHar", "JohLen")))
private val players: PlayersTA =
    sortedMapOf("PauMcc" to pauMcc, "GeoHar" to geoHar, "RinSta" to rinSta, "JohLen" to johLen)

class HatTest : StringSpec({
    "hat should build from JSON" {
        hatJsonStringToHat(JSON_STRING).shouldBe(testHat)
    }
    "hat should NOT build a sortedSet from BAD JSON" {
        shouldThrowAny {
            hatJsonStringToHat(BAD_JSON_STRING)
        }
    }
    "hat should NOT build a non-Set from DUPLICATES" {
        hatJsonStringToHat(DUPLICATES).shouldBe(testHat)
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
})
