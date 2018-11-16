package redpointKotlin

typealias RawString = String?
typealias Scrubbed = String
typealias ScrubbedOrNull = String?
typealias ErrorString = String?
typealias ResultPair = Pair<ErrorString, ScrubbedOrNull>

object RosterStringCheck {

    private fun applyOrError(f: (scrubbed: Scrubbed) -> ResultPair, resultPair: ResultPair): ResultPair {
        val (l, r) = resultPair
        return if (l == null && r != null) {
            f(r)
        } else Pair(l, null)
    }

    // Remove the spaces between CSVs and any final \n
    fun scrub(rawString: String): String {
        return rawString
                .trimEnd()
                .replace(", ", ",")
    }

    // Split string into lines
    fun lines(scrubbed: String): List<String> = scrubbed.split('\n')

    private val <T> List<T>.head: T
        get() = first()

    private val <T> List<T>.tail: List<T>
        get() = drop(1)

    // Remove name from player Array
    fun removeName(player: List<String>): List<String> = listOf(player.head).plus((player.tail).tail)

    // Ensure string is not nil, empty or only spaces. Returns a scrubbed string
    fun nonBlankString(rawString: RawString): ResultPair {
        return if (rawString == null ||
                rawString
                        .trim()
                        .isEmpty()) {
            ResultPair("the roster string was null, empty or only spaces", null)
        } else {
            ResultPair(null, scrub(rawString))
        }
    }

    // A string of newlines >= 4?
    fun validLengthString(scrubbed: Scrubbed): ResultPair {
        return if (scrubbed.filter { it == '\n' }.length >= 4) {
            ResultPair(null, scrubbed)
        } else ResultPair("roster string is not long enough", null)
    }


    // Ensure that raw-string is scrubbed and fully valid
    fun scrubbedRosterString(rawString: RawString): ResultPair {
        var result = nonBlankString(rawString)
        result = applyOrError(this::validLengthString, result)
        return result
    }

}
