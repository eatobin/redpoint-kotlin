package redpointKotlin

data class GiftPair(val givee: Givee, val giver: Giver)

data class Player(val playerName: PlayerName, val giftHistory: GiftHistory) {

    companion object {

        fun <E> Iterable<E>.updated(index: Int, elem: E) =
            mapIndexed { i, existing -> if (i == index) elem else existing }

        fun getGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair =
            giftHistory[giftYear]

        fun setGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory =
            giftHistory.updated(giftYear, giftPair)

        fun setGiftHistoryInPlayer(player: Player, giftHistory: GiftHistory): Player =
            player.copy(giftHistory = giftHistory)

        fun addYearInPlayer(player: Player, playerKey: PlayerKey): Player {
            val ngh = player.giftHistory.plusElement(GiftPair(playerKey, playerKey))
            return setGiftHistoryInPlayer(player, ngh)
        }

    }

}

data class Roster(val rosterName: RosterName, val rosterYear: RosterYear, val players: Players) {

    companion object {

        fun getPlayerInRoster(roster: Roster, playerKey: PlayerKey): Player? = roster.players[playerKey]

        fun getPlayerNameInRoster(roster: Roster, playerKey: PlayerKey): PlayerName {
            val mPlayer = getPlayerInRoster(roster, playerKey)
            return mPlayer?.playerName ?: "null"
        }

        fun getGiftPairInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): GiftPair {
            val mPlayer = getPlayerInRoster(roster, playerKey)
            return if (mPlayer != null) {
                Player.getGiftPairInGiftHistory(mPlayer.giftHistory, giftYear)
            } else GiftPair("null", "null")
        }

        fun getGiveeInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): Givee {
            val mPlayer = getPlayerInRoster(roster, playerKey)
            return if (mPlayer != null) {
                mPlayer.giftHistory[giftYear].givee
            } else "null"
        }

        fun getGiverInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): Giver {
            val mPlayer = getPlayerInRoster(roster, playerKey)
            return if (mPlayer != null) {
                mPlayer.giftHistory[giftYear].giver
            } else "null"
        }

        fun addYearInPlayers(players: Players): Players {
            var nPlayers: Players = emptyMap()
            for ((k, v) in players) {
                nPlayers = nPlayers.plus(mapOf(k to Player.addYearInPlayer(v, k)))
            }
            return nPlayers
        }

//        fun setGiftPairInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Roster {
//            val mPlayer = getPlayerInRoster(roster, playerKey)
//            return if (mPlayer != null) {
//                val gh = mPlayer.giftHistory
//                val ngh = Player.setGiftPairInGiftHistory(gh, giftYear, giftPair)
//                val nPlayer = Player.setGiftHistoryInPlayer(mPlayer, ngh)
//                val mutPlayers = roster.players.toMutableMap()
//                mutPlayers.put(playerKey, nPlayer)
//
//            } else roster
//        }

//        fun setGiveeInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Roster {
//            val giver = getGiverInRoster(roster, playerKey, giftYear)
//            val giftPair: GiftPair = GiftPair(givee, giver)
//            return setGift
//        }


    }

}
