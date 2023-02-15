typealias PlayerNameTA = String

data class Player(val playerName: PlayerNameTA, val giftHistory: GiftHistoryTA) {
// companion object {
//     fun addYearPlayer(player: Player, playerKey: PlayerKeyT): Player {
//         val gh = player.giftHistory
//         val ngh = GiftHistory.addYear(gh, playerKey)
//         return setGiftHistory(player, ngh)
//     }

//     fun setGiftHistory(player: Player, giftHistory: GiftHistoryT): Player =
//         player.copy(giftHistory = giftHistory)
// }
}
