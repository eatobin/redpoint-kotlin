import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias GiftYear = Int

object GiftHistory {
    fun giftHistoryJsonStringToGiftHistory(jsonString: String): List<GiftPair> = Json.decodeFromString(jsonString)

    fun giftHistoryAddYear(giftHistory: List<GiftPair>, playerKey: String): List<GiftPair> =
        giftHistory.plus(GiftPair(playerKey, playerKey))

    fun giftHistoryUpdateGiftHistory(
        giftHistory: List<GiftPair>, giftYear: GiftYear, giftPair: GiftPair
    ): List<GiftPair> {
        val mgh = giftHistory.toMutableList()
        mgh[giftYear] = giftPair
        return mgh.toList()
    }
}

