import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object GiftHistory {
    fun giftHistoryJsonStringToGiftHistory(jsonString: String): List<GiftPair> = Json.decodeFromString(jsonString)

    fun giftHistoryAddYear(giftHistory: List<GiftPair>, playerKey: String): List<GiftPair> =
        giftHistory.plus(GiftPair(playerKey, playerKey))
//
//    fun getGiftPair(giftHistory: List<GiftPair>, giftYear: Int): GiftPair = giftHistory[giftYear]
//
//    fun setGiftPair(giftHistory: List<GiftPair>, giftYear: Int, giftPair: GiftPair): List<GiftPair> {
//        val gh = giftHistory.toMutableList()
//        gh[giftYear] = giftPair
//        return gh.toList()
//    }
}
