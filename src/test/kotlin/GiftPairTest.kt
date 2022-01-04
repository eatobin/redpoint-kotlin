import GiftPair.Companion.setGivee
import GiftPair.Companion.setGiver
import kotlin.test.Test
import kotlin.test.assertEquals

private val giftPair: GiftPair = GiftPair("JohLen", "GeoHar")

class GiftPairTest {
    @Test
    fun testSetGivee() {
        assertEquals(GiftPair("JohLenX", "GeoHar"), setGivee("JohLenX", giftPair))
    }

    @Test
    fun testSetGiver() {
        assertEquals(GiftPair("JohLen", "GeoHarX"), setGiver("GeoHarX", giftPair))
    }
}
