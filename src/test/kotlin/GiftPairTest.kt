import GiftPair.Companion.giftPairJsonStringToGiftPair
import GiftPair.Companion.giftPairUpdateGivee
import GiftPair.Companion.giftPairUpdateGiver
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private const val JSON_STRING: String = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
private const val BAD_JSON_STRING: String = "{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}"
private const val BAD_JSON_STRING_2: String = "{\"giveeX\":\"GeoHar\",\"giver\":\"JohLen\"}"
private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

class GiftPairTest : StringSpec({
    "giftPair should build from JSON" {
        giftPairJsonStringToGiftPair(JSON_STRING).shouldBe(giftPair)
    }
    "giftPair should NOT build from BAD JSON" {
        shouldThrowAny {
            giftPairJsonStringToGiftPair(BAD_JSON_STRING)
        }
    }
    "giftPair should NOT build from BAD JSON 2" {
        shouldThrowAny {
            giftPairJsonStringToGiftPair(BAD_JSON_STRING_2)
        }
    }
    "giftPair should update a givee" {
        giftPairUpdateGivee("NewBee", giftPair).shouldBe(GiftPair("NewBee", "JohLen"))
    }
    "giftPair should update a giver" {
        giftPairUpdateGiver("NewBee", giftPair).shouldBe(GiftPair("GeoHar", "NewBee"))
    }
})
