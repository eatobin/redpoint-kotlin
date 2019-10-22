package redpointKotlin

data class GiftPair(val givee: Givee, val giver: Giver)

data class Player(val playerName: PlayerName, val giftHistory: GiftHistory)

data class Roster(val rosterName: RosterName, val rosterYear: RosterYear, val players: Players) {

    companion object {

        fun getPlayer(roster: Roster, playerKey: PlayerKey): Player? = roster.players[playerKey]

        fun getGiftPairInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): GiftPair {
            val mPlayer = getPlayer(roster, playerKey)
            return if (mPlayer != null) {
                mPlayer.giftHistory[giftYear]
            } else GiftPair("null", "null")
        }

    }

}
