import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json



// data class Player(val playerName: PlayerNameT, val giftHistory: GiftHistoryT) {

// companion object {
//     fun addYearPlayer(player: Player, playerKey: PlayerKeyT): Player {
//         val gh = player.giftHistory
//         val ngh = GiftHistory.addYear(gh, playerKey)
//         return setGiftHistory(player, ngh)
//     }

//     fun setGiftHistory(player: Player, giftHistory: GiftHistoryT): Player =
//         player.copy(giftHistory = giftHistory)
// }
//}
