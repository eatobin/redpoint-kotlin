import GiftPair.Companion.giftPairJsonStringToGiftPair
import GiftPair.Companion.giftPairUpdateGivee
import GiftPair.Companion.giftPairUpdateGiver
import kotlin.test.Test
import kotlin.test.assertEquals

private const val JSON_STRING: String = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

class GiftPairTest {
    @Test
    fun testGiftPairJsonStringToGiftPair() {
        assertEquals(giftPair, giftPairJsonStringToGiftPair(JSON_STRING))
    }

    @Test
    fun testGiftPairUpdateGivee() {
        assertEquals(GiftPair("NewBee", "JohLen"), giftPairUpdateGivee("NewBee", giftPair))
    }

    @Test
    fun testGiftPairUpdateGiver() {
        assertEquals(GiftPair("GeoHar", "NewBee"), giftPairUpdateGiver("NewBee", giftPair))
    }
}
