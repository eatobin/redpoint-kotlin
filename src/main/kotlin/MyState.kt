import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias RosterNameTA = String
typealias RosterYearTA = Int
typealias QuitTA = String

@Serializable
data class MyState(
    val rosterName: RosterNameTA,
    val rosterYear: RosterYearTA,
    val players: PlayersTA,
    val giftYear: GiftYearTA,
    val giveeHat: HatTA,
    val giverHat: HatTA,
    val maybeGivee: GiveeTA?,
    val maybeGiver: GiverTA?,
    val discards: DiscardsTA,
    val quit: QuitTA
) {
    companion object {
        fun myStateJsonStringToMyState(jsonString: JsonStringTA): MyState {
            val unsortedState: MyState = Json.decodeFromString(jsonString)
            return MyState(
                rosterName = unsortedState.rosterName,
                rosterYear = unsortedState.rosterYear,
                players = unsortedState.players.toSortedMap(),
                giftYear = unsortedState.giftYear,
                giveeHat = unsortedState.giveeHat.toSortedSet(),
                giverHat = unsortedState.giverHat.toSortedSet(),
                maybeGivee = unsortedState.maybeGivee,
                maybeGiver = unsortedState.maybeGiver,
                discards = unsortedState.discards.toSortedSet(),
                quit = unsortedState.quit
            )
        }

        fun myStateDrawPuck(hat: HatTA): PlayerKeyTA? {
            return if (hat.isEmpty()) {
                null
            } else {
                hat.asSequence().shuffled().find { true }
            }
        }
    }
}

