
plugins {
    id("adapters.publish-conventions")
}

dependencies {
    val shadowOnly by configurations.creating
    fun shadowOnlyNMS(version: String) = shadowOnly(project(":spigot:v$version", "reobf"))

    api(projects.spigot.base)
    shadowOnlyNMS("1_17_R1")
    shadowOnlyNMS("1_18_R1")
    shadowOnlyNMS("1_18_R2")
    shadowOnlyNMS("1_19_R1")
    shadowOnlyNMS("1_19_R2")
    shadowOnlyNMS("1_19_R3")
    shadowOnlyNMS("1_20_R1")
}

tasks {
    shadowJar {
        configurations.add(project.configurations.getByName("shadowOnly"))
    }
}
