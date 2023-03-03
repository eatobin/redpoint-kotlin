import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.*

private const val JSON_STRING: JsonStringTA = "[\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
private const val BAD_JSON_STRING: JsonStringTA = "[\"RinSta\"\"JohLen\"\"GeoHar\"\"PauMcc\"]"
private const val DUPLICATES: JsonStringTA = "[\"RinSta\",\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
private val testHat: SortedSet<PlayerKeyTA> = sortedSetOf("JohLen", "RinSta", "PauMcc", "GeoHar")

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
})
