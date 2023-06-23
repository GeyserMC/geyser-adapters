
plugins {
    id("adapters.publish-conventions")
    id("io.papermc.paperweight.userdev")
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.17.1-R0.1-SNAPSHOT")
}

description = "v1_17_R1"
