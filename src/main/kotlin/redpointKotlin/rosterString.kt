package redpointKotlin

typealias GoodInt = Int

typealias RawString = String
typealias Scrubbed = String

object RosterStringCheck {

    fun applyOrError(resultPair: Pair<GoodInt, String?>): String = if (resultPair.second == null) "null" else "not-null"

    // Remove the spaces between CSVs and any final \n
    fun scrub(rawString: RawString): String {
        return rawString
                .trimEnd()
                .replace(", ", ",")
    }

    // Split string into lines
    fun lines(scrubbed: Scrubbed): List<String> = scrubbed.split('\n')
}
