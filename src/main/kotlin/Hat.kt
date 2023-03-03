import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*

typealias HatTA = SortedSet<PlayerKeyTA>
typealias DiscardsTA = HatTA

fun hatJsonStringToHat(jsonString: JsonStringTA): HatTA {
    val myUnsortedSet: Set<PlayerKeyTA> = Json.decodeFromString(jsonString)
    return myUnsortedSet.toSortedSet()
}
