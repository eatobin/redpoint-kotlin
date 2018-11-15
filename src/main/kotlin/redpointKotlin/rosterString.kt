package com.eatobin.redpointKotlin.rosterString

fun applyOrError(resultPair: Pair<Int, Int>): String = if (resultPair.first == 1) "one" else "two"
