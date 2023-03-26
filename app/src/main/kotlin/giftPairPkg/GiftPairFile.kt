package giftPairPkg

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias PlayerKeyTA = String
typealias GiveeTA = PlayerKeyTA
typealias GiverTA = PlayerKeyTA
typealias JsonStringTA = String

@Serializable
data class GiftPairDC(val givee: GiveeTA, val giver: GiverTA) {
    companion object {
        fun giftPairJsonStringToGiftPair(jsonString: JsonStringTA): GiftPairDC = Json.decodeFromString(jsonString)
        fun giftPairUpdateGivee(givee: GiveeTA, giftPairDC: GiftPairDC): GiftPairDC = giftPairDC.copy(givee = givee)
        fun giftPairUpdateGiver(giver: GiverTA, giftPairDC: GiftPairDC): GiftPairDC = giftPairDC.copy(giver = giver)
    }
}
