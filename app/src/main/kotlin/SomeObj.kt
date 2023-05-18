// https://stackoverflow.com/questions/44515031/is-kotlin-pass-by-value-or-pass-by-reference

data class SomeObj(var x: Int = 1)

fun takeObj(o: SomeObj) {
    o.x = 2
}

data class AnotherObj(val x: Int = 100)

fun takeObjAgain(o: AnotherObj): AnotherObj {
    return o.copy(x = 200)
}

fun main() {
    val obj = SomeObj()
    println("obj before call: $obj") // SomeObj(x=1)
    takeObj(obj)
    println("obj after call: $obj") // SomeObj(x=2)

    val obj2 = AnotherObj()
    println("AnotherObj before call: $obj2") // AnotherObj(x=100)
    takeObjAgain(obj2)
    println("AnotherObj after call: $obj2") // AnotherObj(x=100)
    val newish = takeObjAgain(obj2)
    println("AnotherObj after assignment: $newish") // AnotherObj(x=200)
}
