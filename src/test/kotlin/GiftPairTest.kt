import GiftPair.Companion.giftPairJsonStringToGiftPair
import GiftPair.Companion.giftPairUpdateGivee
import GiftPair.Companion.giftPairUpdateGiver
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GiftPairTest : StringSpec({
    val jsonString = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
    val badJsonString = "{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}"
    val badJsonString2 = "{\"giveeX\":\"GeoHar\",\"giver\":\"JohLen\"}"
    val giftPair = GiftPair("GeoHar", "JohLen")

    "giftPair should build from JSON" {
        giftPairJsonStringToGiftPair(jsonString).shouldBe(giftPair)
    }
    "giftPair should NOT build from BAD JSON" {
        shouldThrowAny {
            giftPairJsonStringToGiftPair(badJsonString)
        }
    }
    "giftPair should NOT build from BAD JSON 2" {
        shouldThrowAny {
            giftPairJsonStringToGiftPair(badJsonString2)
        }
    }
    "giftPair should update a givee" {
        giftPairUpdateGivee("NewBee", giftPair).shouldBe(GiftPair("NewBee", "JohLen"))
    }
    "giftPair should update a giver" {
        giftPairUpdateGiver("NewBee", giftPair).shouldBe(GiftPair("GeoHar", "NewBee"))
    }
})
