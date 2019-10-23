package redpointKotlin

data class GiftPair(val givee: Givee, val giver: Giver)

data class Player(val playerName: PlayerName, val giftHistory: GiftHistory) {

    companion object {

        fun getGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair =
            giftHistory[giftYear]

        private fun setGiftHistoryInPlayer(player: Player, giftHistory: GiftHistory): Player =
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

    }

}
