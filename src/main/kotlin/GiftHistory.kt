import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias GiftHistoryTA = List<GiftPair>

object GiftHistory {
    fun giftHistoryJsonStringToGiftHistory(jsonString: JsonStringTA): GiftHistoryTA = Json.decodeFromString(jsonString)

    fun giftHistoryAddYear(giftHistory: GiftHistoryTA, playerKey: PlayerKeyTA): GiftHistoryTA =
        giftHistory.plus(GiftPair(playerKey, playerKey))

    fun giftHistoryUpdateGiftHistory(
        giftHistory: GiftHistoryTA, giftYear: GiftYearTA, giftPair: GiftPair
    ): GiftHistoryTA {
        return giftHistory.updated(giftYear, giftPair)
    }
}

