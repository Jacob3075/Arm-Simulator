/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Alternate day buildsat 10:00") {
    startOn {
        gitPush { enabled = false }
        schedule { cron("0 10 * * */2") }
    }
    gradlew("openjdk:11", "build")
}