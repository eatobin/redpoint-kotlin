import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*

typealias PlayersTA = SortedMap<PlayerKeyTA, Player>

fun playersJsonStringToPlayers(jsonString: JsonStringTA): PlayersTA {
    val myUnsortedMap: Map<PlayerKeyTA, Player> = Json.decodeFromString(jsonString)
    return myUnsortedMap.toSortedMap()
}

//fun playersUpdatePlayer(playerKey: PlayerKeyTA, player: Player, players: PlayersTA): PlayersTA {
//    val mutPlayers = players.toMutableMap()
//    mutPlayers[playerKey] = player
//    return mutPlayers.toSortedMap()
//}
//
//fun playersGetPlayerName(playerKey: PlayerKeyTA, players: PlayersTA): PlayerNameTA {
//    return players.getValue(playerKey).playerName
//}
//
//fun playersAddYear(players: PlayersTA): PlayersTA {
//    val mutPlayers = players.toMutableMap()
//    mutPlayers.forEach {
//        val gh = it.value.giftHistory
//        val ngh = giftHistoryAddYear(it.key, gh)
//        val nplr = playerUpdateGiftHistory(ngh, it.value)
//        (it.key to nplr)
//    }
//    return mutPlayers.toSortedMap()
//}
//
//fun playersGetMyGivee(selfKey: PlayerKeyTA, players: PlayersTA, giftYear: GiftYearTA): GiveeTA {
//    return players.getValue(selfKey).giftHistory[giftYear].givee
//}
//
//fun playersGetMyGiver(selfKey: PlayerKeyTA, players: PlayersTA, giftYear: GiftYearTA): GiverTA {
//    return players.getValue(selfKey).giftHistory[giftYear].giver
//}
//
//fun playersSetGiftPair(
//    playerKey: PlayerKeyTA, giftYear: GiftYearTA, giftPair: GiftPair, players: PlayersTA
//): PlayersTA {
//    val plr = players.getValue(playerKey)
//    val gh = plr.giftHistory
//    val ngh = giftHistoryUpdateGiftHistory(giftYear, giftPair, gh)
//    val nplr = playerUpdateGiftHistory(ngh, plr)
//    return playersUpdatePlayer(playerKey, nplr, players)
//}
//
//private fun playersUpdateMyGiveeGiver(
//    selfKey: PlayerKeyTA, giveeGiver: PlayerKeyTA, giftYear: GiftYearTA, players: PlayersTA
//): PlayersTA {
//    val plr = players.getValue(selfKey)
//    val gh = plr.giftHistory
//    val gp = gh[giftYear]
//    val ngp = giftPairUpdateGivee(giveeGiver, gp)
//    return playersSetGiftPair(selfKey, giftYear, ngp, players)
//}
//
//fun playersUpdateMyGivee(
//    selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA
//): PlayersTA {
//    return playersUpdateMyGiveeGiver(selfKey, givee, giftYear, players)
//}
//
//fun playersUpdateMyGiver(
//    selfKey: PlayerKeyTA, giver: GiverTA, giftYear: GiftYearTA, players: PlayersTA
//): PlayersTA {
//    return playersUpdateMyGiveeGiver(selfKey, giver, giftYear, players)
//}
