package rules

import giftHistory.GiftYearTA
import giftPair.GiveeTA
import giftPair.PlayerKeyTA
import players.PlayersTA
import players.playersGetMyGivee

fun rules2GiveeNotRepeat(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
    val giveesInYears: MutableList<GiveeTA> = mutableListOf()
    val past: List<GiftYearTA> = ((giftYear - 1) downTo (giftYear - 4)).filterNot { it < 0 }
    for (i in past.indices) {
        giveesInYears.add(playersGetMyGivee(selfKey, players, past[i]))
    }
    return !(giveesInYears.contains(givee))
}
