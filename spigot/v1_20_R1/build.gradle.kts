
plugins {
    id("adapters.paperweight-conventions")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
}
