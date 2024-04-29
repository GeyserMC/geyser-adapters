
plugins {
    id("adapters.paperweight-conventions")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(projects.spigot.base)
    paperweight.paperDevBundle("1.20.5-R0.1-SNAPSHOT")
}
