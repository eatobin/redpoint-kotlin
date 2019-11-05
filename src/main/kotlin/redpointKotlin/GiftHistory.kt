package redpointKotlin

object GiftHistory {
    fun addYear(giftHistory: GiftHistoryT, playerKey: PlayerKeyT) =
        giftHistory.add(GiftPair(playerKey, playerKey))
}




