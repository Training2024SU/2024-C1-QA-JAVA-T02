plugins {
    id("java")
}

group = "org.moreno.cristian"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.apache.poi:poi-ooxml:5.2.5")
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.3")
    implementation ("org.slf4j:slf4j-api:1.7.32")
    implementation ("mysql:mysql-connector-java:8.0.28")
    implementation ("org.mongodb:mongodb-driver-sync:4.3.3")
}

tasks.test {
    useJUnitPlatform()
}