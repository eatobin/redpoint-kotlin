package player

import giftHistory.GiftHistoryTA
import giftPair.JsonStringTA
import kotlinx.serialization.Serializable
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
