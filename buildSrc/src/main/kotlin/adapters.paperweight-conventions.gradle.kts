
plugins {
    id("adapters.base-conventions")
    id("io.papermc.paperweight.userdev")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}
