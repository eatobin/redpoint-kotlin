package redpointKotlin

object Roster {
    fun getRosterName(entity: Entity): String? = entity["roster-name"]
}
