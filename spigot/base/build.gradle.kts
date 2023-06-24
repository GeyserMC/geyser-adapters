
plugins {
    id("adapters.publish-conventions")
}

dependencies {
    api(projects.common)
    compileOnly("io.papermc.paper", "paper-api", "1.17.1-R0.1-SNAPSHOT") {
        attributes {
            attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 16)
        }
    }
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}
