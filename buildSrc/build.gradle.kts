plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("io.papermc.paperweight:paperweight-userdev:2.0.0-beta.18")
    implementation("com.github.johnrengelman", "shadow", "8.1.1")
    implementation("architectury-plugin:architectury-plugin.gradle.plugin:3.4-SNAPSHOT")
    implementation("dev.architectury:architectury-loom:1.10-SNAPSHOT")
}

repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.architectury.dev/")
    maven("https://maven.neoforged.net/releases/")
}
