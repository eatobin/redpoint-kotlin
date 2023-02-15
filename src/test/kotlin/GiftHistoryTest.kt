import GiftHistory.giftHistoryJsonStringToGiftHistory
import GiftHistory.giftHistoryAddYear
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

private const val JSON_STRING: String = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
private val giftHistory: List<GiftPair> = listOf(GiftPair("GeoHar", "JohLen"))

class GiftHistoryTest {
    @Test
    fun testGiftHistoryJsonStringToGiftHistory() {
        assertEquals(giftHistory, giftHistoryJsonStringToGiftHistory(JSON_STRING))
    }

    @Test
    fun testGiftHistoryAddYear() {
        assertEquals(
            listOf(
                GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")
            ), giftHistoryAddYear(giftHistory, "NewBee")
        )
    }

//     "A GiftHistory should add a new year" {
//         addYear(giftHistory, "NewBee").shouldBe(
//             listOf(
//                 GiftPair("JohLen", "GeoHar"),
//                 GiftPair("NewBee", "NewBee")
//             )
//         )
//     }
//
//     "A GiftHistory should return a giftPair" {
//         getGiftPair(giftHistory, 0).shouldBe(GiftPair("JohLen", "GeoHar"))
//     }
//
//     "A GiftHistory should update a giftHistory" {
//         setGiftPair(giftHistory, 0, GiftPair("me", "you")).shouldBe(listOf(GiftPair("me", "you")))
//     }
// }
}
