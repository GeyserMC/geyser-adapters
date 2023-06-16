
plugins {
    id("io.papermc.paperweight.userdev")
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.18.2-R0.1-SNAPSHOT")
}

description = "v1_18_R2"
