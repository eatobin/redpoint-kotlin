import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Project(val name: String, val language: String)

fun main() {
    // Serializing objects
    val data = Project("kotlinx.serialization", "Kotlin")
    val string = Json.encodeToString(data)
    println(string) // {"name":"kotlinx.serialization","language":"Kotlin"}
    // Deserializing back into objects
    val obj = Json.decodeFromString<Project>(string)
    println(obj) // Project(name=kotlinx.serialization, language=Kotlin)
    val numbers: List<Int> = listOf(1, 2, 3, 4)
    val numString: String = Json.encodeToString(numbers)
    println("numString: $numString")
    val numbers2: List<Int> = Json.decodeFromString(numString)
    println("numbers2: $numbers2")
    val numbers99: List<Int> = Json.decodeFromString(listOf(11, 22, 33, 44).toString())
    println("numbers99: $numbers99")
}
