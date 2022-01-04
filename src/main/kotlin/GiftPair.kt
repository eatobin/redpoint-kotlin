data class GiftPair(val givee: String, val giver: String) {

    companion object {
        fun setGivee(givee: String, giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)
        fun setGiver(giver: String, giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
    }
}
