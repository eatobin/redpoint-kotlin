package redpointKotlin

typealias RawString = String?
typealias Scrubbed = String
typealias ScrubbedOrNull = String?
typealias ErrorString = String?
typealias ResultPair = Pair<ErrorString, ScrubbedOrNull>

private fun applyOrError(f: (Scrubbed) -> ResultPair, resultPair: ResultPair): ResultPair {
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

// test
fun rosterInfoLinePresent(scrubbed: Scrubbed): ResultPair {
    return if (nonBlankString(lines(scrubbed).head).first == null) {
        ResultPair(null, scrubbed)
    } else ResultPair("the roster info line is blank", null)
}

// Return the raw-string if a name value is present
fun namePresent(scrubbed: Scrubbed): ResultPair {
    val infoStringList = lines(scrubbed).head.split(",")
    return if (nonBlankString(infoStringList.head).first == null) {
        ResultPair(null, scrubbed)
    } else ResultPair("the name value is missing", null)
}

// Return the info-string if a year value is present
fun yearPresent(scrubbed: Scrubbed): ResultPair {
    val infoStringList = lines(scrubbed).head.split(",")
    return if (infoStringList.count() == 2) {
        ResultPair(null, scrubbed)
    } else ResultPair("the year value is missing", null)
}

// Return the raw-info-string if the year text all digits
fun yearTextAllDigits(scrubbed: Scrubbed): ResultPair {
    val year = lines(scrubbed).head.split(",").last()
    return if (year.toIntOrNull() != null) {
        ResultPair(null, scrubbed)
    } else ResultPair("the year value is not all digits", null)
}


// Ensure that raw-string is scrubbed and fully valid
fun scrubbedRosterString(rawString: RawString): ResultPair {
    var result = nonBlankString(rawString)
    result = applyOrError(::validLengthString, result)
    result = applyOrError(::rosterInfoLinePresent, result)
    result = applyOrError(::namePresent, result)
    result = applyOrError(::yearPresent, result)
    result = applyOrError(::yearTextAllDigits, result)
    return result
}
