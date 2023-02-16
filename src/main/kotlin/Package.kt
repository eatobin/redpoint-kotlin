// used in GiftPair
typealias PlayerKeyTA = String
typealias GiveeTA = PlayerKeyTA
typealias GiverTA = PlayerKeyTA
typealias JsonStringTA = String

// used in GiftHistory



fun GiftHistoryTA.updated(giftYear: GiftYearTA, giftPair: GiftPair) =
    mapIndexed { i, existing -> if (i == giftYear) giftPair else existing }
// fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }

// used in Player
typealias PlayerNameTA = String

// typealias PlayersT = MutableMap<PlayerKeyT, Player>

// typealias RosterNameT = String
// typealias RosterYearT = Int
