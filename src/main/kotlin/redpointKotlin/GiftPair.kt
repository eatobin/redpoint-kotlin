package redpointKotlin

data class GiftPair(val givee: GiveeT, val giver: GiverT) {

    companion object {
        fun setGivEeEr(giftPair: GiftPair, giv: GivT, eEeR: EeErT): GiftPair =
            if (eEeR == "ee") giftPair.copy(givee = giv) else giftPair.copy(giver = giv)
    }
}
