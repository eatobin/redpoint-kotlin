import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias PlayerNameTA = String

@Serializable
data class Player(val playerName: PlayerNameTA, val giftHistory: GiftHistoryTA) {
    companion object {
        fun playerJsonStringToPlayer(jsonString: JsonStringTA): Player = Json.decodeFromString(jsonString)
//     fun addYearPlayer(player: Player, playerKey: PlayerKeyT): Player {
//         val gh = player.giftHistory
//         val ngh = GiftHistory.addYear(gh, playerKey)
//         return setGiftHistory(player, ngh)
//     }

//     fun setGiftHistory(player: Player, giftHistory: GiftHistoryT): Player =
//         player.copy(giftHistory = giftHistory)
    }
}
