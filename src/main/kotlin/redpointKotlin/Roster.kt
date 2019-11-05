package redpointKotlin

//data class Roster(val rosterName: RosterName, val rosterYear: RosterYear, val players: Players) {
//
//    companion object {

//        fun getPlayerInRoster(roster: Roster, playerKey: PlayerKey): Player? = roster.players[playerKey]
//
//        fun getPlayerNameInRoster(roster: Roster, playerKey: PlayerKey): PlayerName {
//            val mPlayer = getPlayerInRoster(roster, playerKey)
//            return mPlayer?.playerName ?: "null"
//        }
//
//        fun getGiftPairInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): GiftPair {
//            val mPlayer = getPlayerInRoster(roster, playerKey)
//            return if (mPlayer != null) {
//                Player.getGiftPairInGiftHistory(mPlayer.giftHistory, giftYear)
//            } else GiftPair("null", "null")
//        }
//
//        fun getGiveeInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): Givee {
//            val mPlayer = getPlayerInRoster(roster, playerKey)
//            return if (mPlayer != null) {
//                mPlayer.giftHistory[giftYear].givee
//            } else "null"
//        }
//
//        fun getGiverInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): Giver {
//            val mPlayer = getPlayerInRoster(roster, playerKey)
//            return if (mPlayer != null) {
//                mPlayer.giftHistory[giftYear].giver
//            } else "null"
//        }
//
//        fun addYearInPlayers(players: Players): Players {
//            val nPlayers: Players = mutableMapOf()
//            for ((k, v) in players) {
//                nPlayers[k] = Player.addYearInPlayer(v, k)
//            }
//            return nPlayers
//        }
//
//        fun setGiftPairInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Roster {
//            val mPlayer = getPlayerInRoster(roster, playerKey)
//            return if (mPlayer != null) {
//                val gh = mPlayer.giftHistory
//                gh[giftYear] = giftPair
//
//            } else roster
//        }

//        fun setGiveeInRoster(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Roster {
//            val giver = getGiverInRoster(roster, playerKey, giftYear)
//            val giftPair: GiftPair = GiftPair(givee, giver)
//            return setGift
//        }

//    }

//}
