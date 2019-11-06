package redpointKotlin

object GiftHistory {
    fun addYear(giftHistory: GiftHistoryT, playerKey: PlayerKeyT) {
        giftHistory.add(GiftPair(playerKey, playerKey))
    }

    fun getGiftPair(giftHistory: GiftHistoryT, giftYear: GiftYearT): GiftPair =
        giftHistory[giftYear]

    fun setGiftPair(giftHistory: GiftHistoryT, giftYear: GiftYearT, giftPair: GiftPair) {
        giftHistory[giftYear] = giftPair
    }
}
