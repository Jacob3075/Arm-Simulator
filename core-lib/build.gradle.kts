plugins {
    kotlin("jvm")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.junit.platform:junit-platform-commons:1.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation ("org.amshove.kluent:kluent:1.65")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit:1.4.32")

    testImplementation("io.mockk:mockk:1.10.6")
}

tasks.test {
    useJUnitPlatform()
}
