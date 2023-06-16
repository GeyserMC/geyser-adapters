pipeline {
    agent any
    tools {
        gradle 'Gradle 8'
        jdk 'Java 17'
    }
    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '20'))
    }
    stages {
        stage ('Build') {
            steps {
                rtGradleRun(
                    usesPlugin: true,
                    tool: 'Gradle 8',
                    buildFile: 'build.gradle.kts',
                    tasks: 'clean build',
                )
            }
            post {
                success {
                    archiveArtifacts artifacts: 'spigot/**/build/libs/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            deleteDir()
        }
    }
}
