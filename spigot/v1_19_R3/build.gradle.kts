
plugins {
    id("io.papermc.paperweight.userdev")
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.19.4-R0.1-SNAPSHOT")
}

description = "v1_19_R3"
