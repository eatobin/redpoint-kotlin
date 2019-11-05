package redpointKotlin

object GiftHistory {
    fun addYear(giftHistory: GiftHistoryT, playerKey: PlayerKeyT): GiftHistoryT {
        giftHistory.add(GiftPair(playerKey, playerKey))
        return giftHistory
    }

    fun getGiftPair(giftHistory: GiftHistoryT, giftYear: GiftYearT): GiftPair =
        giftHistory[giftYear]

    fun setGiftPair(giftHistory: GiftHistoryT, giftYear: GiftYearT, giftPair: GiftPair): GiftHistoryT {
        giftHistory[giftYear] = giftPair
        return giftHistory
    }
}
