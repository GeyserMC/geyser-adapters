pipeline {
    agent any
    tools {
        maven 'Maven 3'
        jdk 'Java 17'
    }
    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '20'))
    }
    stages {
        stage ('Build') {
            steps {
                sh 'git submodule update --init --recursive'
                sh 'curl https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -o BuildTools.jar'
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.15.2'
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.16.4'
                sh 'mvn ca.bkaw:paper-nms-maven-plugin:1.2.1:init -pl :v1_17_R1'
                sh 'mvn ca.bkaw:paper-nms-maven-plugin:1.2.1:init -pl :v1_18_R1'
                sh 'mvn ca.bkaw:paper-nms-maven-plugin:1.2.1:init -pl :v1_18_R2'
                sh 'mvn ca.bkaw:paper-nms-maven-plugin:1.2.1:init -pl :v1_19_R1'
                sh 'mvn ca.bkaw:paper-nms-maven-plugin:1.2.1:init -pl :v1_19_R2'
                sh 'mvn ca.bkaw:paper-nms-maven-plugin:1.2.1:init -pl :v1_19_R3'
                sh 'mvn clean package -B'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'spigot/**/target/*.jar', excludes: 'spigot/**/target/original-*.jar', fingerprint: true
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
