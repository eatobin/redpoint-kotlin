package rules

import giftHistory.GiftYearTA
import giftPair.GiveeTA
import giftPair.PlayerKeyTA
import players.PlayersTA
import players.playersGetMyGivee

fun rules1GiveeNotSelf(selfKey: PlayerKeyTA, givee: GiveeTA): Boolean = selfKey != givee

fun rules1GiveeNotRecip(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
    val giveeIsGivingTo = playersGetMyGivee(givee, players, giftYear)
    return selfKey != giveeIsGivingTo
}
