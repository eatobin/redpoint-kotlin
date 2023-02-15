import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias PlayerKey = String
typealias Givee = PlayerKey
typealias Giver = PlayerKey
typealias JsonString = String

@Serializable
data class GiftPair(val givee: Givee, val giver: Giver) {
    companion object {
        fun giftPairJsonStringToGiftPair(jsonString: JsonString): GiftPair = Json.decodeFromString(jsonString)
        fun giftPairUpdateGivee(givee: Givee, giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)
        fun giftPairUpdateGiver(giver: Giver, giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
    }
}
