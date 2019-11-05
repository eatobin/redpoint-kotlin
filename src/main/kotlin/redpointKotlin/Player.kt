package redpointKotlin

//data class Player(val playerName: PlayerName, val giftHistory: GiftHistory) {
//
//    companion object {
//
//        fun getGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair =
//            giftHistory[giftYear]
//
//        fun setGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory {
//            giftHistory[giftYear] = giftPair
//            return giftHistory
//        }
//
//        fun setGiftHistoryInPlayer(player: Player, giftHistory: GiftHistory): Player =
//            player.copy(giftHistory = giftHistory)
//
//        fun addYearInPlayer(player: Player, playerKey: PlayerKey): Player {
//            player.giftHistory.add(GiftPair(playerKey, playerKey))
//            return setGiftHistoryInPlayer(player, player.giftHistory)
//        }
//
//    }
//
//}
