import GiftHistory.giftHistoryAddYear
import GiftHistory.giftHistoryUpdateGiftHistory
import Player.Companion.playerUpdateGiftHistory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

//
//package com.eatobin.redpointscala
//
//import com.eatobin.redpointscala.GiftHistory.{GiftYearTA, giftHistoryAddYear, giftHistoryUpdateGiftHistory}
//import com.eatobin.redpointscala.GiftPair._
//import com.eatobin.redpointscala.Player.{PlayerNameTA, playerUpdateGiftHistory}
//import io.circe.Error
//import io.circe.generic.auto._
//import io.circe.parser._
//
//import scala.collection.immutable.SortedMap
//
//object Players {
//    type PlayersTA = SortedMap[PlayerKeyTA, Player]


//    def playersUpdateMyGivee(selfKey: PlayerKeyTA)(givee: GiveeTA)(giftYear: GiftYearTA)(players: PlayersTA): PlayersTA = {
//        val ngp = giftPairUpdateGivee(givee)(players(selfKey).giftHistory(giftYear))
//        playersSetGiftPair(selfKey)(giftYear)(ngp)(players)
//    }
//
//    def playersUpdateMyGiver(selfKey: PlayerKeyTA)(giver: GiverTA)(giftYear: GiftYearTA)(players: PlayersTA): PlayersTA = {
//        val ngp = giftPairUpdateGiver(giver)(players(selfKey).giftHistory(giftYear))
//        playersSetGiftPair(selfKey)(giftYear)(ngp)(players)
//    }
//}

typealias PlayersTA = Map<PlayerKeyTA, Player>

object Players {

    fun playersJsonStringToPlayers(jsonString: JsonStringTA): PlayersTA = Json.decodeFromString(jsonString)

    fun playersUpdatePlayer(playerKey: PlayerKeyTA, player: Player, players: PlayersTA): PlayersTA {
        val mutPlayers = players.toMutableMap()
        mutPlayers[playerKey] = player
        return mutPlayers.toMap()
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
            (it.key to nplr)
        }
        return mutPlayers.toMap()
    }

    fun playersGetMyGivee(selfKey: PlayerKeyTA, players: PlayersTA, giftYear: GiftYearTA): GiveeTA {
        return players.getValue(selfKey).giftHistory[giftYear].givee
    }

    fun playersGetMyGiver(selfKey: PlayerKeyTA, players: PlayersTA, giftYear: GiftYearTA): GiverTA {
        return players.getValue(selfKey).giftHistory[giftYear].giver
    }

    fun playersSetGiftPair(
        playerKey: PlayerKeyTA, giftYear: GiftYearTA, giftPair: GiftPair, players: PlayersTA
    ): PlayersTA {
        val mutPlayers = players.toMutableMap()
        val plr = mutPlayers.getValue(playerKey)
        val gh = plr.giftHistory
        val ngh = giftHistoryUpdateGiftHistory(gh, giftYear, giftPair)
        val nplr = playerUpdateGiftHistory(ngh, plr)
        return playersUpdatePlayer(playerKey, nplr, mutPlayers).toMap()
    }
}
