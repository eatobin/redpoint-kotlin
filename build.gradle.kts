plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "com.eatobin"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.create("uberJar", Jar::class) {
    group = "build"
    description = "Creates a self-contained fat JAR of the application that can be run."
    manifest.attributes["Main-Class"] = "MainKt"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val dependencies = configurations.runtimeClasspath.get().map(::zipTree)
    from(dependencies)
    with(tasks.jar.get())
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
