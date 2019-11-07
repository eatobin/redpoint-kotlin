package redpointKotlin

typealias GiveeT = String
typealias GiverT = String
typealias GivT = String
typealias EeErT = String

typealias GiftHistoryT = List<GiftPair>
typealias PlayerKeyT = String
typealias GiftYearT = Int

fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }

typealias PlayerNameT = String

typealias PlayersT = Map<PlayerKeyT, Player>

//typealias RosterNameT = String
//typealias RosterYearT = Int
