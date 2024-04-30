
plugins {
    id("adapters.base-conventions")
    id("io.papermc.paperweight.userdev")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    assemble {
        // according to paperweight-test-plugin
        dependsOn(reobfJar)
    }
}
