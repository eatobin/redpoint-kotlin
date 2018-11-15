package redpointKotlin

typealias GoodInt = Int

object RosterStringCheck {
    fun applyOrError(resultPair: Pair<GoodInt, Int>): String = if (resultPair.first == 1) "one" else "two"
}
