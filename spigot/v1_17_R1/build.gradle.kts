
plugins {
    id("io.papermc.paperweight.userdev")
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.17.1-R0.1-SNAPSHOT")
}

description = "v1_17_R1"
