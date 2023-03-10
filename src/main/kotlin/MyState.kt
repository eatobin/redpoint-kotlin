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
        fun myStateJsonStringToMyState(jsonString: JsonStringTA): MyState = Json.decodeFromString(jsonString)
    }
}

