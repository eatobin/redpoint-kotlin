typealias NonEmptyRawString = String
typealias RawStringOrNull = String?
typealias Scrubbed = String
typealias ScrubbedOrNull = String?
typealias ErrorString = String?

data class EatResult(val error: ErrorString, val result: ScrubbedOrNull)

fun applyOrError(f: (Scrubbed) -> EatResult, eatResult: EatResult): EatResult {
    val (l, r) = eatResult
    return if (l == null && r != null) {
        f(r)
    } else EatResult(l, null)
}

// Remove the spaces between CSVs and any final \n
fun scrub(nonEmptyRawString: NonEmptyRawString): String =
        nonEmptyRawString
                .trimEnd()
                .replace(", ", ",")


// Split string into lines
fun lines(scrubbed: String): List<String> = scrubbed.split('\n')

private val List.head
    get() = first()

private val List.tail
    get() = drop(1)

// Remove name from player Array
fun removeName(player: List<String>): List<String> = listOf(player.head).plus((player.tail).tail)

// Ensure string is not nil, empty or only spaces. Returns a scrubbed string
fun nonBlankString(rawStringOrNull: RawStringOrNull): EatResult {
    return if (rawStringOrNull == null ||
            rawStringOrNull
                    .trim()
                    .isEmpty()) {
        EatResult("the roster string was null, empty or only spaces", null)
    } else {
        EatResult(null, scrub(rawStringOrNull))
    }
}

// A string of newlines >= 4?
fun validLengthString(scrubbed: Scrubbed): EatResult {
    return if (scrubbed.filter { it == '\n' }.length >= 4) {
        EatResult(null, scrubbed)
    } else EatResult("roster string is not long enough", null)
}

// test
fun rosterInfoLinePresent(scrubbed: Scrubbed): EatResult {
    return if (nonBlankString(lines(scrubbed).head).error == null) {
        EatResult(null, scrubbed)
    } else EatResult("the roster info line is blank", null)
}

// Return the raw-string if a name value is present
fun namePresent(scrubbed: Scrubbed): EatResult {
    val infoStringList = lines(scrubbed).head.split(",")
    return if (nonBlankString(infoStringList.head).error == null) {
        EatResult(null, scrubbed)
    } else EatResult("the name value is missing", null)
}

// Return the info-string if a year value is present
fun yearPresent(scrubbed: Scrubbed): EatResult {
    val infoStringList = lines(scrubbed).head.split(",")
    return if (infoStringList.count() == 2) {
        EatResult(null, scrubbed)
    } else EatResult("the year value is missing", null)
}

// Return the raw-info-string if the year text all digits
fun yearTextAllDigits(scrubbed: Scrubbed): EatResult {
    val year = lines(scrubbed).head.split(",").last()
    return if (year.toIntOrNull() != null) {
        EatResult(null, scrubbed)
    } else EatResult("the year value is not all digits", null)
}

// Return the info-string if 1956 <= year <= 2056
fun yearInRange(scrubbed: Scrubbed): EatResult {
    val year = lines(scrubbed).head.split(",").last().toIntOrNull()
    return if (year != null && 1956 <= year && year <= 2056) {
        EatResult(null, scrubbed)
    } else EatResult("not 1956 <= year <= 2056", null)
}

// Given a valid scrubbed-string, return an array of player strings
fun makePlayerList(scrubbed: Scrubbed): List<String> = lines(scrubbed).tail

// Returns all player vectors void of names - symbols only
fun makeOnlySymbols(playersList: List<String>): List<List<String>> =
        playersList.map { it.split(",") }.map { removeName(it) }

// All strings in the arrays are 6 chars long
fun allSixChars(playerSymbols: List<String>): Boolean {
    val count = playerSymbols.count()
    val six = playerSymbols.filter { it.length == 6 }
    return (count == 3 && six.count() == 3)
}

// All of the lists only symbols
fun allListsAllSix(playersLists: List<List<String>>): Boolean {
    val theBools = playersLists.map { allSixChars(it) }
    return theBools.contains(false)
}

// test
fun playersValid(scrubbed: Scrubbed): EatResult {
    val pl = makePlayerList(scrubbed)
    val ps = makeOnlySymbols(pl)
    return if (!allListsAllSix(ps)) {
        EatResult(null, scrubbed)
    } else EatResult("the players sub-string is invalid", null)
}

// Ensure that raw-string is scrubbed and fully valid
fun scrubbedRosterString(rawStringOrNull: RawStringOrNull): EatResult {
    var result = nonBlankString(rawStringOrNull)
    result = applyOrError(::validLengthString, result)
    result = applyOrError(::rosterInfoLinePresent, result)
    result = applyOrError(::namePresent, result)
    result = applyOrError(::yearPresent, result)
    result = applyOrError(::yearTextAllDigits, result)
    result = applyOrError(::yearInRange, result)
    result = applyOrError(::playersValid, result)
    return result
}

//[eric@linux-x2vq redpointKotlin](master)$ kotlinc -script ./scripts/scrubber.kts
val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
println(scrubbedRosterString(bs))
println(scrubbedRosterString(null))
println(scrubbedRosterString("The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc"))
println(scrubbedRosterString("\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString(",2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString("The Beatles\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString("The Beatles, 2014P\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString("The Beatles, 2096\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString("The Beatles, 1896\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString("The Beatles, 2014\nRinStaX, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
println(scrubbedRosterString("The Beatles, 2014\nRinSta, Ringo Starr, JohLen\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"))
