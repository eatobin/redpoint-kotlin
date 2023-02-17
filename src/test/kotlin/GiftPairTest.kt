import GiftPair.Companion.giftPairJsonStringToGiftPair
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private const val JSON_STRING: String = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
private const val BAD_JSON_STRING: String = "{\"givee\"\"GeoHar\",\"giver\":\"JohLen\"}"
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
})


//     @Test
//     fun testGiftPairUpdateGivee() {
//         assertEquals(GiftPair("NewBee", "JohLen"), giftPairUpdateGivee("NewBee", giftPair))
//     }

//     @Test
//     fun testGiftPairUpdateGiver() {
//         assertEquals(GiftPair("GeoHar", "NewBee"), giftPairUpdateGiver("NewBee", giftPair))
//     }
// }
