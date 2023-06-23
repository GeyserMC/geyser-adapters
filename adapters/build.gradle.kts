
plugins {
    id("adapters.publish-conventions")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

description = "adapters"
