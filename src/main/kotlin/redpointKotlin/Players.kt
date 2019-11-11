package redpointKotlin

object Players {
    fun getPlayer(players: PlayersT, playerKey: PlayerKeyT): Player =
        players[playerKey] ?: Player("null", listOf(GiftPair("null", "null")))

    fun addYearPlayers(players: PlayersT): PlayersT {
        val newPlayers = mutableMapOf<PlayerKeyT, Player>()
        for ((playerKey, player) in players) {
            newPlayers[playerKey] = Player.addYearPlayer(player, playerKey)
        }
        return newPlayers
    }
}
