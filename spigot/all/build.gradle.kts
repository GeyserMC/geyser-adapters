
plugins {
    id("adapters.publish-conventions")
}

// configuration for shading NMS implementations, but not adding them to any classpath, to avoid java version troubles
val shadowOnly: Configuration by configurations.creating

dependencies {

    // target the reobf configuration otherwise we get the mapped classes
    fun adapter(version: String) = shadowOnly(project(":spigot:v$version", "reobf"))

    api(projects.spigot.base)
    adapter("1_17_R1")
    adapter("1_18_R1")
    adapter("1_18_R2")
    adapter("1_19_R1")
    adapter("1_19_R2")
    adapter("1_19_R3")
    adapter("1_20_R1")
    adapter("1_20_R2")
    adapter("1_20_R3")
    adapter("1_20_R4")
}

tasks {
    shadowJar {
        // consumers of spigot-all must depend on the shaded jar to get the implementations
        configurations.add(shadowOnly)
    }
}
