plugins {
    id("java")

}

group = "org.bjss"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.mockito/mockito-inline
    testImplementation("org.mockito:mockito-inline:5.2.0")
// https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
    testImplementation("org.mockito:mockito-junit-jupiter:5.6.0")
// https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("org.mockito:mockito-core:5.6.0")

    // If using JUnit Jupiter
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("junit:junit:4.13.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")

    // https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation("com.googlecode.json-simple:json-simple:1.1.1")


}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest.attributes["Main-Class"] = "org.bjss.PriceBasketTest"
}

