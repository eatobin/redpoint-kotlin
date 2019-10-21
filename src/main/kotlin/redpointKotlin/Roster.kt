package redpointKotlin

data class GiftPair(val givee: Givee, val giver: Giver)

data class Player(val playerName: PlayerName, val giftHistory: GiftHistory)

data class Roster(val rosterName: RosterName, val rosterYear: RosterYear, val players: Players)