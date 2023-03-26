package giftHistoryTestPkg

import giftHistoryPkg.giftHistoryAddYear
import giftHistoryPkg.giftHistoryJsonStringToGiftHistory
import giftHistoryPkg.giftHistoryUpdateGiftHistory
import giftPairPkg.GiftPairDC
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GiftHistoryTest : StringSpec({
    val jsonString = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
    val badJsonString = "[{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}]"
    val badJsonString2 = "[{\"giveeX\":\"GeoHar\",\"giver\":\"JohLen\"}]"
    val giftHistory: List<GiftPairDC> = listOf(GiftPairDC("GeoHar", "JohLen"))

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
        giftHistoryAddYear("NewBee", giftHistory).shouldBe(
            listOf(
                GiftPairDC("GeoHar", "JohLen"),
                GiftPairDC("NewBee", "NewBee")
            )
        )
    }
    "giftHistory should update a year's giftPair" {
        giftHistoryUpdateGiftHistory(0, GiftPairDC("me", "you"), giftHistory).shouldBe(listOf(GiftPairDC("me", "you")))
    }
})
