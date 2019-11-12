package redpointKotlin

object Players {
    fun getPlayer(players: PlayersT, playerKey: PlayerKeyT): Player =
        players.getOrDefault(playerKey, Player("null", listOf(GiftPair("null", "null"))))

    fun setPlayer(players: PlayersT, playerKey: PlayerKeyT, player: Player): PlayersT {
        players[playerKey] = player
        return players
    }

    fun addYearPlayers(players: PlayersT): PlayersT {
        val newPlayers = mutableMapOf<PlayerKeyT, Player>()
        for ((playerKey, player) in players) {
            newPlayers[playerKey] = Player.addYearPlayer(player, playerKey)
        }
        return newPlayers
    }
}
