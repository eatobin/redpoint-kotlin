package redpointKotlin

object Players {
    fun getPlayer(players: PlayersT, playerKey: PlayerKeyT): Player =
        players[playerKey] ?: Player("null", listOf(GiftPair("null", "null")))

    // TODO
    fun setPlayer(players: PlayersT, playerKey: PlayerKeyT, player: Player): Players {
        players.asSequence().iterator().forEach { k, existing -> if (k == playerKey) player else existing }
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

private fun <T> Iterator<T>.forEach(operation: (T, Any?) -> Unit) {

}
