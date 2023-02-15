data class GiftPair(val givee: String, val giver: String) {
    companion object {
//        private val gson = Gson()
//        fun giftPairJsonStringToGiftPair(jsonString: String): GiftPair =
//            gson.fromJson(jsonString, GiftPair::class.java)

        fun giftPairUpdateGivee(givee: String, giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)
        fun giftPairUpdateGiver(giver: String, giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
    }
}
