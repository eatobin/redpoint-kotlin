package players

import giftHistory.GiftYearTA
import giftHistory.giftHistoryAddYear
import giftHistory.giftHistoryUpdateGiftHistory
import giftPair.*
import giftPair.GiftPair.Companion.giftPairUpdateGivee
import giftPair.GiftPair.Companion.giftPairUpdateGiver
import kotlinx.serialization.json.Json
import player.Player
import player.Player.Companion.playerUpdateGiftHistory
import player.PlayerNameTA

typealias PlayersTA = Map<PlayerKeyTA, Player>

fun playersJsonStringToPlayers(jsonString: JsonStringTA): PlayersTA =
    Json.decodeFromString<PlayersTA>(jsonString).toSortedMap()

fun playersUpdatePlayer(playerKey: PlayerKeyTA, player: Player, players: PlayersTA): PlayersTA {
    val mutPlayers = players.toMutableMap()
    mutPlayers[playerKey] = player
    return mutPlayers.toSortedMap()
}

fun playersGetPlayerName(playerKey: PlayerKeyTA, players: PlayersTA): PlayerNameTA {
    return players.getValue(playerKey).playerName
}

fun playersAddYear(players: PlayersTA): PlayersTA {
    val mutPlayers = players.toMutableMap()
    mutPlayers.forEach {
        val gh = it.value.giftHistory
        val ngh = giftHistoryAddYear(it.key, gh)
        val nplr = playerUpdateGiftHistory(ngh, it.value)
        mutPlayers[it.key] = nplr
    }
    return mutPlayers.toSortedMap()
}

fun playersGetMyGivee(selfKey: PlayerKeyTA, players: PlayersTA, giftYear: GiftYearTA): GiveeTA {
    return players.getValue(selfKey).giftHistory[giftYear].givee
}

fun playersGetMyGiver(selfKey: PlayerKeyTA, players: PlayersTA, giftYear: GiftYearTA): GiverTA {
    return players.getValue(selfKey).giftHistory[giftYear].giver
}

private fun playersSetGiftPair(
    playerKey: PlayerKeyTA, giftYear: GiftYearTA, giftPair: GiftPair, players: PlayersTA
): PlayersTA {
    val plr = players.getValue(playerKey)
    val gh = plr.giftHistory
    val ngh = giftHistoryUpdateGiftHistory(giftYear, giftPair, gh)
    val nplr = playerUpdateGiftHistory(ngh, plr)
    return playersUpdatePlayer(playerKey, nplr, players)
}

fun playersUpdateMyGivee(
    selfKey: PlayerKeyTA, givee: PlayerKeyTA, giftYear: GiftYearTA, players: PlayersTA
): PlayersTA {
    val plr = players.getValue(selfKey)
    val gh = plr.giftHistory
    val gp = gh[giftYear]
    val ngp = giftPairUpdateGivee(givee, gp)
    return playersSetGiftPair(selfKey, giftYear, ngp, players)
}

fun playersUpdateMyGiver(
    selfKey: PlayerKeyTA, giver: PlayerKeyTA, giftYear: GiftYearTA, players: PlayersTA
): PlayersTA {
    val plr = players.getValue(selfKey)
    val gh = plr.giftHistory
    val gp = gh[giftYear]
    val ngp = giftPairUpdateGiver(giver, gp)
    return playersSetGiftPair(selfKey, giftYear, ngp, players)
}
