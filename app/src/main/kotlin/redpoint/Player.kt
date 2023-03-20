package redpoint

import GiftHistoryTA
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias PlayerNameTA = String

@Serializable
data class Player(val playerName: PlayerNameTA, val giftHistory: GiftHistoryTA) {
    companion object {
        fun playerJsonStringToPlayer(jsonString: JsonStringTA): Player = Json.decodeFromString(jsonString)
        fun playerUpdateGiftHistory(giftHistory: GiftHistoryTA, player: Player): Player =
            player.copy(giftHistory = giftHistory)
    }
}
