fun rulesGiveeNotSelf(selfKey: PlayerKeyTA, givee: GiveeTA): Boolean = selfKey != givee

fun rulesGiveeNotRecip(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
    val giveeIsGivingTo = playersGetMyGivee(givee, players, giftYear)
    return selfKey != giveeIsGivingTo
}


//TODO
//fun rulesGiveeNotRepeat(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: PlayersTA): Boolean {
//    val past: List<GiftYearTA> = ((giftYear - 1) downTo (giftYear - 4)).filterNot { it < 0 }
//    val giveeInYear = playersGetMyGivee(selfKey,players,giftYear)
////    val giveeInYear: GiftYearTA => GiveeTA = (giftYear: GiftYearTA) => playersGetMyGivee(selfKey)(players)(giftYear)
////    val giveesInYears: Vector[GiveeTA] = past.map(gy => giveeInYear(gy))
////    return !giveesInYears.contains(givee)
//}
