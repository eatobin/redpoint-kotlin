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

        fun myStateStartNewYear(state: MyState): MyState {
            val freshHat: HatTA = hatMakeHat(state.players)
            return MyState(
                rosterName = state.rosterName,
                rosterYear = state.rosterYear,
                players = playersAddYear(state.players),
                giftYear = state.giftYear + 1,
                giveeHat = freshHat,
                giverHat = freshHat,
                maybeGivee = myStateDrawPuck(freshHat),
                maybeGiver = myStateDrawPuck(freshHat),
                discards = sortedSetOf(),
                quit = state.quit
            )
        }

        fun myStateGiveeIsFailure(state: MyState): MyState {
            val giveeToRemove: GiveeTA = state.maybeGivee ?: "none"
            val diminishedGiveeHat: HatTA = hatRemovePuck(giveeToRemove, state.giveeHat)
            return MyState(
                rosterName = state.rosterName,
                rosterYear = state.rosterYear,
                players = state.players,
                giftYear = state.giftYear,
                giveeHat = diminishedGiveeHat,
                giverHat = state.giverHat,
                maybeGivee = myStateDrawPuck(diminishedGiveeHat),
                maybeGiver = state.maybeGiver,
                discards = hatDiscardGivee(giveeToRemove, state.discards),
                quit = state.quit
            )
        }

        fun myStateGiveeIsSuccess(state: MyState): MyState {
            val currentGiver: GiverTA = state.maybeGiver ?: "none"
            val currentGivee: GiveeTA = state.maybeGivee ?: "none"
            val updatedGiveePlayers: PlayersTA =
                playersUpdateMyGivee(currentGiver, currentGivee, state.giftYear, state.players)
            return MyState(
                rosterName = state.rosterName,
                rosterYear = state.rosterYear,
                players = playersUpdateMyGiver(currentGivee, currentGiver, state.giftYear, updatedGiveePlayers),
                giftYear = state.giftYear,
                giveeHat = hatRemovePuck(currentGivee, state.giveeHat),
                giverHat = state.giverHat,
                maybeGivee = null,
                maybeGiver = state.maybeGiver,
                discards = state.discards,
                quit = state.quit
            )
        }
    }
}

