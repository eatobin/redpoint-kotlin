import GiftHistory.addYear
import kotlin.test.Test
import kotlin.test.assertEquals

val giftHistory: List<GiftPair> = listOf(GiftPair("JohLen", "GeoHar"))

class GiftHistoryTest {
    @Test
    fun testAddYear() {
        assertEquals(
            listOf(
                GiftPair("JohLen", "GeoHar"), GiftPair("NewBee", "NewBee")
            ), addYear(giftHistory, "NewBee")
        )
    }
}
//     "A GiftHistory should add a new year" {
//         addYear(giftHistory, "NewBee").shouldBe(
//             listOf(
//                 GiftPair("JohLen", "GeoHar"),
//                 GiftPair("NewBee", "NewBee")
//             )
//         )
//     }

//     "A GiftHistory should return a giftPair" {
//         getGiftPair(giftHistory, 0).shouldBe(GiftPair("JohLen", "GeoHar"))
//     }

//     "A GiftHistory should update a giftHistory" {
//         setGiftPair(giftHistory, 0, GiftPair("me", "you")).shouldBe(listOf(GiftPair("me", "you")))
//     }
// })
