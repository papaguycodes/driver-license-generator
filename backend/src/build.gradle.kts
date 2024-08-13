plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-core:2.5.0")
    implementation("io.ktor:ktor-server-netty:2.5.0")
    implementation("io.ktor:ktor-serialization-jackson:2.5.0")
    implementation("com.github.javafaker:faker:2.8.0") // For generating fake data (optional)
}

application {
    mainClass.set("com.papaguycodes.driver-license-generator.ApplicationKt")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.papaguycodes.driver-license-generator.ApplicationKt"
    }
}
