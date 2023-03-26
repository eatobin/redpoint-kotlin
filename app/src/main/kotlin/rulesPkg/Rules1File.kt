package rulesPkg

import giftHistoryPkg.GiftYearTA
import giftPairPkg.GiveeTA
import giftPairPkg.PlayerKeyTA
import playersPkg.PlayersTA
import playersPkg.playersGetMyGivee

fun rules1GiveeNotSelf(selfKey: PlayerKeyTA, givee: GiveeTA): Boolean = selfKey != givee

fun rules1GiveeNotRecip(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
    val giveeIsGivingTo = playersGetMyGivee(givee, players, giftYear)
    return selfKey != giveeIsGivingTo
}
