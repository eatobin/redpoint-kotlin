import GiftPair.Companion.giftPairJsonStringToGiftPair
import GiftPair.Companion.giftPairUpdateGivee
import GiftPair.Companion.giftPairUpdateGiver
import kotlin.test.Test
import kotlin.test.assertEquals

private const val JS: JsonString = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

class GiftPairTest {
    @Test
    fun testGiftPairUpdateGivee() {
        assertEquals(GiftPair("NewBee", "JohLen"), giftPairUpdateGivee("NewBee", giftPair))
    }

    @Test
    fun testGiftPairUpdateGiver() {
        assertEquals(GiftPair("GeoHar", "NewBee"), giftPairUpdateGiver("NewBee", giftPair))
    }

    @Test
    fun testGiftPairJsonStringToGiftPair() {
        assertEquals(giftPair, giftPairJsonStringToGiftPair(JS))
    }
}
