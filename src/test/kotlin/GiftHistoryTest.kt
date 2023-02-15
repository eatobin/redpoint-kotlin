import GiftHistory.giftHistoryAddYear
import GiftHistory.giftHistoryJsonStringToGiftHistory
import GiftHistory.giftHistoryUpdateGiftHistory
import kotlin.test.Test
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

    @Test
    fun testGiftHistoryUpdateGiftHistory() {
        assertEquals(
            listOf(
                GiftPair("me", "you")
            ), giftHistoryUpdateGiftHistory(giftHistory, 0, GiftPair("me", "you"))
        )
    }


}
