import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GiftHistoryTest : StringSpec({
    val jsonString = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
    val badJsonString = "[{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}]"
    val badJsonString2 = "[{\"giveeX\":\"GeoHar\",\"giver\":\"JohLen\"}]"
    val giftHistory: List<GiftPair> = listOf(GiftPair("GeoHar", "JohLen"))

    "giftHistory should build from JSON" {
        giftHistoryJsonStringToGiftHistory(jsonString).shouldBe(giftHistory)
    }
    "giftHistory should NOT build from BAD JSON" {
        shouldThrowAny {
            giftHistoryJsonStringToGiftHistory(badJsonString)
        }
    }
    "giftHistory should NOT build from BAD JSON 2" {
        shouldThrowAny {
            giftHistoryJsonStringToGiftHistory(badJsonString2)
        }
    }
    "giftHistory should add a year" {
        giftHistoryAddYear("NewBee", giftHistory)
            .shouldBe(listOf(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
    }
    "giftHistory should update a year's giftPair" {
        giftHistoryUpdateGiftHistory(0, GiftPair("me", "you"), giftHistory)
            .shouldBe(listOf(GiftPair("me", "you")))
    }
})
