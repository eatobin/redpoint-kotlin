package rules

import giftPair.GiveeTA
import giftPair.PlayerKeyTA
import redpoint.GiftYearTA
import redpoint.PlayersTA
import redpoint.playersGetMyGivee

fun rules1GiveeNotSelf(selfKey: PlayerKeyTA, givee: GiveeTA): Boolean = selfKey != givee

fun rules1GiveeNotRecip(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
    val giveeIsGivingTo = playersGetMyGivee(givee, players, giftYear)
    return selfKey != giveeIsGivingTo
}
