
plugins {
    id("adapters.publish-conventions")
}

dependencies {
    val shadowOnly by configurations.creating

    api(projects.spigot.base)
    shadowOnly(projects.spigot.v117R1)
    shadowOnly(projects.spigot.v118R1)
    shadowOnly(projects.spigot.v118R2)
    shadowOnly(projects.spigot.v119R1)
    shadowOnly(projects.spigot.v119R2)
    shadowOnly(projects.spigot.v119R3)
    shadowOnly(projects.spigot.v120R1)
}

tasks {
    shadowJar {
        configurations.add(project.configurations.getByName("shadowOnly"))
    }
}
