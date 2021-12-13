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
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.12.2'
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.15.2'
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.16.1'
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.16.3'
                sh '/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.16.4'
                sh '/usr/lib/jvm/adoptopenjdk-16-hotspot-amd64/bin/java -jar BuildTools.jar --rev 1.17'
                sh 'java -jar BuildTools.jar --rev 1.18'
                sh 'mvn clean package -B'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'spigot/**/target/*.jar', excludes: 'spigot/**/target/original-*.jar', fingerprint: true
                }
            }
        }

        stage ('Deploy') {
            when {
                branch "master"
            }

            steps {
                rtMavenDeployer(
                        id: "maven-deployer",
                        serverId: "opencollab-artifactory",
                        releaseRepo: "maven-releases",
                        snapshotRepo: "maven-snapshots"
                )
                rtMavenResolver(
                        id: "maven-resolver",
                        serverId: "opencollab-artifactory",
                        releaseRepo: "maven-deploy-release",
                        snapshotRepo: "maven-deploy-snapshot"
                )
                rtMavenRun(
                        pom: 'pom.xml',
                        goals: 'javadoc:jar source:jar install -B -DskipTests',
                        deployerId: "maven-deployer",
                        resolverId: "maven-resolver"
                )
                rtPublishBuildInfo(
                        serverId: "opencollab-artifactory"
                )
            }
        }
    }

    post {
        always {
            deleteDir()
        }
    }
}
