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

    fun getPlayerNamePlayers(players: PlayersT, playerKey: PlayerKeyT): PlayerNameT =
        getPlayer(players, playerKey).playerName

    fun getGivEeErPlayers(players: PlayersT, playerKey: PlayerKeyT, giftYear: GiftYearT, eEeR: EeErT): GivT {
        val plr = getPlayer(players, playerKey)
        val gh = plr.giftHistory
        val gp = gh[giftYear]
        return if (eEeR == "ee") gp.givee else gp.giver
    }

    fun setGivEeErPlayers(
        players: PlayersT,
        playerKey: PlayerKeyT,
        giftYear: GiftYearT,
        giv: GivT,
        eEeR: EeErT
    ): PlayersT {
        val plr = getPlayer(players, playerKey)
        val gh = plr.giftHistory
        val gp = gh[giftYear]
        val ngp = GiftPair.setGivEeEr(gp, giv, eEeR)
        val ngh = GiftHistory.setGiftPair(gh, giftYear, ngp)
        val nplr = Player.setGiftHistory(plr, ngh)
        setPlayer(players, playerKey, nplr)
        return players
    }
}
