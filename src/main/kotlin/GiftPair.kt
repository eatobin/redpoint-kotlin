import com.google.gson.Gson

data class GiftPair(val givee: Givee, val giver: Giver) {
    companion object {
        private var gson = Gson()
        fun giftPairUpdateGivee(givee: Givee, giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)
        fun giftPairUpdateGiver(giver: Giver, giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
        fun giftPairJsonStringToGiftPair(jsonString: JsonString): GiftPair =
            gson.fromJson(jsonString, GiftPair::class.java)
    }
}
