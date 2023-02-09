plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "eatobin.com"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.beust:klaxon:5.6")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
