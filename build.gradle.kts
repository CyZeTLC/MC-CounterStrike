plugins {
    id("java")
}

group = "de.cyzetlc.mccs"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.minestom:minestom-snapshots:461c56e749")
    implementation("org.slf4j:slf4j-simple:2.0.14")
}

tasks.test {
    useJUnitPlatform()
}