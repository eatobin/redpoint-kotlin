package giftPairTestPkg

import giftPairPkg.GiftPairDC
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GiftPairTestC : StringSpec({
    val jsonString = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
    val badJsonString = "{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}"
    val badJsonString2 = "{\"giveeX\":\"GeoHar\",\"giver\":\"JohLen\"}"
    val giftPairDC = GiftPairDC("GeoHar", "JohLen")

    "giftPair should build from JSON" {
        GiftPairDC.giftPairJsonStringToGiftPair(jsonString).shouldBe(giftPairDC)
    }
    "giftPair should NOT build from BAD JSON" {
        shouldThrowAny {
            GiftPairDC.giftPairJsonStringToGiftPair(badJsonString)
        }
    }
    "giftPair should NOT build from BAD JSON 2" {
        shouldThrowAny {
            GiftPairDC.giftPairJsonStringToGiftPair(badJsonString2)
        }
    }
    "giftPair should update a givee" {
        GiftPairDC.giftPairUpdateGivee("NewBee", giftPairDC).shouldBe(GiftPairDC("NewBee", "JohLen"))
    }
    "giftPair should update a giver" {
        GiftPairDC.giftPairUpdateGiver("NewBee", giftPairDC).shouldBe(GiftPairDC("GeoHar", "NewBee"))
    }
})
