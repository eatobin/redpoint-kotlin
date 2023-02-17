import GiftHistory.giftHistoryAddYear
import GiftHistory.giftHistoryJsonStringToGiftHistory
import GiftHistory.giftHistoryUpdateGiftHistory
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private const val JSON_STRING: String = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
private const val BAD_JSON_STRING: String = "[{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}]"
private val giftHistory: List<GiftPair> = listOf(GiftPair("GeoHar", "JohLen"))

class GiftHistoryTest : StringSpec({
    "giftHistory should build from JSON" {
        giftHistoryJsonStringToGiftHistory(JSON_STRING).shouldBe(giftHistory)
    }
    "giftHistory should NOT build from BAD JSON" {
        shouldThrowAny {
            giftHistoryJsonStringToGiftHistory(BAD_JSON_STRING)
        }
    }
    "giftHistory should add a year" {
        giftHistoryAddYear("NewBee", giftHistory)
            .shouldBe(listOf(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
    }
    "giftHistory should update a year's giftPair" {
        giftHistoryUpdateGiftHistory(giftHistory, 0, GiftPair("me", "you"))
            .shouldBe(listOf(GiftPair("me", "you")))
    }
})
