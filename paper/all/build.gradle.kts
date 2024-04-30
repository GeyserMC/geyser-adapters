
plugins {
    id("adapters.publish-conventions")
}

// configuration for shading NMS implementations, but not adding them to any classpath, to avoid java version troubles
val shadowOnly: Configuration by configurations.creating

dependencies {
    api(projects.paper.base)
    shadowOnly(projects.paper.v766)
}

tasks {
    shadowJar {
        // consumers of spigot-all must depend on the shaded jar to get the implementations
        configurations.add(shadowOnly)
    }
}
