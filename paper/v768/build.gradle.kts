
plugins {
    id("adapters.paper.paperweight-conventions")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(projects.paper.base)
    paperweight.paperDevBundle("1.21.3-R0.1-SNAPSHOT")
}
