
plugins {
    id("adapters.publish-conventions")
}

dependencies {
    api(projects.common)
    compileOnly("io.papermc.paper", "paper-api", "1.20.5-R0.1-SNAPSHOT") {
        attributes {
            attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 21)
        }
    }
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
}
