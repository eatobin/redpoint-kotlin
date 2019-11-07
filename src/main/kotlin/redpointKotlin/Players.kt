package redpointKotlin

object Players {
    // FIXME
    fun addYearPlayers(players: PlayersT): PlayersT =
        for ((playerKey, player) < -players) yield
    playerKey -> Player.addYearPlayer(player, playerKey)
}
