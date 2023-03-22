package rules

import redpoint.*

fun giveeNotSelf(selfKey: PlayerKeyTA, givee: GiveeTA): Boolean = selfKey != givee

fun giveeNotRecip(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
    val giveeIsGivingTo = playersGetMyGivee(givee, players, giftYear)
    return selfKey != giveeIsGivingTo
}
