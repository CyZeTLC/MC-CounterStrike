plugins {
    id("java")
}

group = "de.cyzetlc.mccs"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(26))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:1.18.44")
    annotationProcessor("org.projectlombok:lombok:1.18.44")

    testCompileOnly("org.projectlombok:lombok:1.18.44")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.44")

    implementation("net.minestom:minestom:2026.03.25-1.21.11")
    implementation("org.slf4j:slf4j-simple:2.0.14")

    implementation("de.articdive:jnoise-pipeline:4.1.0")
}

tasks.test {
    useJUnitPlatform()
}