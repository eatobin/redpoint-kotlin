package redpointKotlin

import redpointKotlin.Player.Companion.getGiftHistoryInPlayer

data class GiftPair(val givee: Givee, val giver: Giver) {

    companion object {

        fun getGiveeInGiftPair(giftPair: GiftPair): Givee = giftPair.givee

        fun getGiverInGiftPair(giftPair: GiftPair): Giver = giftPair.giver

    }
}

data class Player(val playerName: PlayerName, val giftHistory: GiftHistory) {

    companion object {

        fun getGiftHistoryInPlayer(player: Player): GiftHistory = player.giftHistory

        fun setGiftHistoryInPlayer(player: Player, giftHistory: GiftHistory): Player =
            player.copy(giftHistory = giftHistory)

        fun addYearInPlayer(player: Player, playerKey: PlayerKey): Player {
            val gh = getGiftHistoryInPlayer(player)
            gh.add(GiftPair(playerKey, playerKey))
            return setGiftHistoryInPlayer(player, gh)
        }

    }
}

data class Roster(val rosterName: RosterName, val rosterYear: RosterYear, val players: Players) {

    companion object {

        fun getPlayerInRoster(roster: Roster, playerKey: PlayerKey): Player? = roster.players[playerKey]


        fun getGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair = giftHistory[giftYear]

        fun getGiftPairInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): GiftPair {
            val mPlayer = getPlayerInRoster(roster, playerKey)
            return if (mPlayer != null) {
                val gh = getGiftHistoryInPlayer(mPlayer)
                getGiftPairInGiftHistory(gh, giftYear)
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


    }

}
