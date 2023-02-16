import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias PlayerKeyTA = String
typealias GiveeTA = PlayerKeyTA
typealias GiverTA = PlayerKeyTA

@Serializable
data class GiftPair(val givee: GiveeTA, val giver: GiverTA) {
    companion object {
        fun giftPairJsonStringToGiftPair(jsonString: JsonStringTA): GiftPair = Json.decodeFromString(jsonString)
        fun giftPairUpdateGivee(givee: GiveeTA, giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)
        fun giftPairUpdateGiver(giver: GiverTA, giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
    }
}
