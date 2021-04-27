job("Arm Simulator") {
    startOn {
        gitPush { enabled = true }
    }
    gradlew("openjdk:11", "build")
}
