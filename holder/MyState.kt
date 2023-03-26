package redpoint

import giftPair.GiveeTA
import giftPair.GiverTA
import giftPair.JsonStringTA
import giftPair.PlayerKeyTA
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import rules.rules1GiveeNotRecip
import rules.rules1GiveeNotSelf
import rules.rules2GiveeNotRepeat

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
            val giveeToRemove: GiveeTA = state.maybeGivee!!
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
            val currentGiver: GiverTA = state.maybeGiver!!
            val currentGivee: GiveeTA = state.maybeGivee!!
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

        fun myStateSelectNewGiver(state: MyState): MyState {
            val giverToRemove: GiverTA = state.maybeGiver!!
            val replenishedGiveeHat: HatTA = hatReturnDiscards(state.discards, state.giveeHat)
            val diminishedGiverHat: HatTA = hatRemovePuck(giverToRemove, state.giverHat)
            return MyState(
                rosterName = state.rosterName,
                rosterYear = state.rosterYear,
                players = state.players,
                giftYear = state.giftYear,
                giveeHat = replenishedGiveeHat,
                giverHat = diminishedGiverHat,
                maybeGivee = myStateDrawPuck(replenishedGiveeHat),
                maybeGiver = myStateDrawPuck(diminishedGiverHat),
                discards = sortedSetOf(),
                quit = state.quit
            )
        }

        fun myStateErrors(state: MyState): List<PlayerKeyTA> {
            val playerKeys: MutableList<PlayerKeyTA> = state.players.keys.toMutableList()
            val playerErrors: MutableList<PlayerKeyTA> = mutableListOf()
            for (playerKeyMe: PlayerKeyTA in playerKeys) {
                val myGiverKey: PlayerKeyTA = playersGetMyGiver(playerKeyMe, state.players, state.giftYear)
                val myGiveeKey: PlayerKeyTA = playersGetMyGivee(playerKeyMe, state.players, state.giftYear)
                if (playerKeyMe == myGiverKey || playerKeyMe == myGiveeKey) playerErrors.add(playerKeyMe)
            }
            return playerErrors.sorted().toList()
        }

        fun myStatePrintResults(state: MyState): MyState {
            println()
            println("%s - Year %d Gifts:".format(state.rosterName, state.rosterYear + state.giftYear))
            println()
            val playerKeys: List<PlayerKeyTA> = state.players.keys.toList()
            for (playerKey: PlayerKeyTA in playerKeys) {
                val playerName = playersGetPlayerName(playerKey, state.players)
                val giveeKey = playersGetMyGivee(playerKey, state.players, state.giftYear)
                val giveeName = playersGetPlayerName(giveeKey, state.players)
                val giverKey = playersGetMyGiver(playerKey, state.players, state.giftYear)

                if (playerKey == giveeKey && playerKey == giverKey) {
                    println("%s is neither **buying** for nor **receiving** from anyone - **ERROR**".format(playerName))
                } else if (playerKey == giverKey) {
                    println("%s is **receiving** from no one - **ERROR**".format(playerName))
                } else if (playerKey == giveeKey) {
                    println("%s is **buying** for no one - **ERROR**".format(playerName))
                } else {
                    println("%s is buying for %s".format(playerName, giveeName))
                }
            }
            if (myStateErrors(state).isNotEmpty()) {
                println()
                println("There is a logic error in this year's pairings.")
                println("Do you see how it occurs?")
                println("If not... call me and I'll explain!")
            }
            return state
        }

        fun myStateAskContinue(state: MyState): MyState {
            println()
            print("Continue? ('q' to quit): ")
            val reply = readln()
            return state.copy(quit = reply)
        }

        fun myStateUpdateAndRunNewYear(state: MyState): MyState {
            val newYearState: MyState = myStateStartNewYear(state)
            return myStateLoop(newYearState)
        }

        private tailrec fun myStateLoop(alteredState: MyState): MyState {
            if (alteredState.maybeGiver != null) {
                if (alteredState.maybeGivee != null) {
                    if (rules1GiveeNotSelf(alteredState.maybeGiver, alteredState.maybeGivee) && rules1GiveeNotRecip(
                            alteredState.maybeGiver,
                            alteredState.maybeGivee,
                            alteredState.giftYear,
                            alteredState.players
                        ) && rules2GiveeNotRepeat(
                            alteredState.maybeGiver,
                            alteredState.maybeGivee,
                            alteredState.giftYear,
                            alteredState.players
                        )
                    ) {
                        return myStateLoop(myStateGiveeIsSuccess(alteredState))
                    } else {
                        return myStateLoop(myStateGiveeIsFailure(alteredState))
                    }
                } else {
                    return myStateLoop(myStateSelectNewGiver(alteredState))
                }
            } else {
                return alteredState
            }
        }
    }
}
