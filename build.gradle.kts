plugins {
    kotlin("jvm") version "1.5.10"
}

allprojects {
    group = "com.jacob"
    version = "1.0-SNAPSHOT"

    repositories {
        jcenter()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }
}
