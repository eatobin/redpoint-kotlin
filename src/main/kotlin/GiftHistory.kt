object GiftHistory {
    fun addYear(giftHistory: List<GiftPair>, playerKey: String): List<GiftPair> =
        giftHistory.plus(GiftPair(playerKey, playerKey))

    fun getGiftPair(giftHistory: List<GiftPair>, giftYear: Int): GiftPair = giftHistory[giftYear]

    fun setGiftPair(giftHistory: List<GiftPair>, giftYear: Int, giftPair: GiftPair): List<GiftPair> {
        val gh = giftHistory.toMutableList()
        gh[giftYear] = giftPair
        return gh.toList()
    }
}
