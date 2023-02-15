import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class GiftPair(val givee: String, val giver: String) {
    companion object {
        fun giftPairJsonStringToGiftPair(jsonString: String): GiftPair = Json.decodeFromString(jsonString)
        fun giftPairUpdateGivee(givee: String, giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)
        fun giftPairUpdateGiver(giver: String, giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
    }
}
