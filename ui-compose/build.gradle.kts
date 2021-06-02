import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "0.4.0"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":core-lib"))
    implementation(compose.desktop.currentOs)
}

tasks.test {
    useJUnitPlatform()
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Arm Simulator"
            packageVersion = "1.0.0"
        }
    }
}
