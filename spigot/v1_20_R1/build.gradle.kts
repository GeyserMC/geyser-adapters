
plugins {
    id("adapters.publish-conventions")
    id("io.papermc.paperweight.userdev")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
}

description = "v1_20_R1"