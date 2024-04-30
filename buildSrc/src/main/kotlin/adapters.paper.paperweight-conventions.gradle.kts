
plugins {
    id("adapters.base-conventions")
    id("io.papermc.paperweight.userdev")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

paperweight {
    reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
}
