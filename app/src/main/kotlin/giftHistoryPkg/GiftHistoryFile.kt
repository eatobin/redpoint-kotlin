package giftHistoryPkg

import giftPairPkg.GiftPairDC
import giftPairPkg.JsonStringTA
import giftPairPkg.PlayerKeyTA
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias GiftHistoryTA = List<GiftPairDC>
typealias GiftYearTA = Int

//fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }
private fun GiftHistoryTA.updated(giftYear: GiftYearTA, giftPair: GiftPairDC) =
    mapIndexed { i, existing -> if (i == giftYear) giftPair else existing }

fun giftHistoryJsonStringToGiftHistory(jsonString: JsonStringTA): GiftHistoryTA = Json.decodeFromString(jsonString)

fun giftHistoryAddYear(playerKey: PlayerKeyTA, giftHistory: GiftHistoryTA): GiftHistoryTA =
    giftHistory.plus(GiftPairDC(playerKey, playerKey))

fun giftHistoryUpdateGiftHistory(
    giftYear: GiftYearTA, giftPair: GiftPairDC, giftHistory: GiftHistoryTA
): GiftHistoryTA {
    return giftHistory.updated(giftYear, giftPair)
}
