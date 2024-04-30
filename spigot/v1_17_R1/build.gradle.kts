
plugins {
    id("adapters.spigot.paperweight-conventions")
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.17.1-R0.1-SNAPSHOT")
}
