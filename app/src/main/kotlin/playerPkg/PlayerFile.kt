package playerPkg

import giftHistoryPkg.GiftHistoryTA
import giftPairPkg.JsonStringTA
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias PlayerNameTA = String

@Serializable
data class PlayerDC(val playerName: PlayerNameTA, val giftHistory: GiftHistoryTA) {
    companion object {
        fun playerJsonStringToPlayer(jsonString: JsonStringTA): PlayerDC = Json.decodeFromString(jsonString)
        fun playerUpdateGiftHistory(giftHistory: GiftHistoryTA, player: PlayerDC): PlayerDC =
            player.copy(giftHistory = giftHistory)
    }
}
