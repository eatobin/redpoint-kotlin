import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias GiftHistoryTA = List<GiftPair>
typealias GiftYearTA = Int

object GiftHistory {
    private fun GiftHistoryTA.updated(giftYear: GiftYearTA, giftPair: GiftPair) =
        mapIndexed { i, existing -> if (i == giftYear) giftPair else existing }
    //fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }

    fun giftHistoryJsonStringToGiftHistory(jsonString: JsonStringTA): GiftHistoryTA = Json.decodeFromString(jsonString)

    fun giftHistoryAddYear(playerKey: PlayerKeyTA, giftHistory: GiftHistoryTA): GiftHistoryTA =
        giftHistory.plus(GiftPair(playerKey, playerKey))

    fun giftHistoryUpdateGiftHistory(
        giftYear: GiftYearTA, giftPair: GiftPair, giftHistory: GiftHistoryTA
    ): GiftHistoryTA {
        return giftHistory.updated(giftYear, giftPair)
    }
}

