package redpoint

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias HatTA = Set<PlayerKeyTA>
typealias DiscardsTA = HatTA

fun hatJsonStringToHat(jsonString: JsonStringTA): HatTA = Json.decodeFromString<HatTA>(jsonString).toSortedSet()

fun hatMakeHat(players: PlayersTA): HatTA = players.keys.toSortedSet()

fun hatRemovePuck(playerKey: PlayerKeyTA, hat: HatTA): HatTA = hat.minus(playerKey).toSortedSet()

fun hatDiscardGivee(givee: GiveeTA, discards: DiscardsTA): HatTA = discards.plus(givee).toSortedSet()

fun hatReturnDiscards(discards: DiscardsTA, geHat: HatTA): HatTA = geHat.plus(discards).toSortedSet()
