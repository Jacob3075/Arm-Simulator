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

    container(displayName = "Arm Sim Container", image = "gradle:jdk11") {
        resources {
            cpu = 1.cpu
            memory = 2500.mb
        }
        
        kotlinScript { api ->
            api.gradlew("build")
        }
    }
}