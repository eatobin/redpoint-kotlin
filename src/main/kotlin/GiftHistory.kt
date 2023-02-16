import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias GiftHistoryTA = List<GiftPair>
typealias GiftYearTA = Int

object GiftHistory {
    private fun GiftHistoryTA.updated(giftYear: GiftYearTA, giftPair: GiftPair) =
        mapIndexed { i, existing -> if (i == giftYear) giftPair else existing }
    //fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }

    fun giftHistoryJsonStringToGiftHistory(jsonString: JsonStringTA): GiftHistoryTA = Json.decodeFromString(jsonString)

    fun giftHistoryAddYear(giftHistory: GiftHistoryTA, playerKey: PlayerKeyTA): GiftHistoryTA =
        giftHistory.plus(GiftPair(playerKey, playerKey))

    fun giftHistoryUpdateGiftHistory(
        giftHistory: GiftHistoryTA, giftYear: GiftYearTA, giftPair: GiftPair
    ): GiftHistoryTA {
        return giftHistory.updated(giftYear, giftPair)
    }
}

