package redpointKotlin

data class GiftPair(val givee: Givee, val giver: Giver) {

    companion object {
        fun setGivEeEr(giftPair: GiftPair, giv: Giv, eEeR: EeEr): GiftPair =
            if (eEeR == "ee") giftPair.copy(givee = giv) else giftPair.copy(giver = giv)
    }
}
